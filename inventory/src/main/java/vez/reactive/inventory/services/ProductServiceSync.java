package vez.reactive.inventory.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vez.reactive.inventory.dto.Order;
import vez.reactive.inventory.dto.OrderStatus;
import vez.reactive.inventory.dto.Product;
import vez.reactive.inventory.repo.ProductRepo;

@Service
public class ProductServiceSync implements ProductService{

    private ProductRepo productRepository;

    @Override
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Order handleOrder(Order order) {
        order.getLineItems().forEach( item -> {
            Product p = productRepository
                    .findById( item.getProductId() )
                    .orElseThrow(
                            () -> new RuntimeException("Could not find the product: " + item.getProductId())
                    );
            if (p.getStock() >= item.getQuantity()) {
                p.setStock(p.getStock() - item.getQuantity());
                productRepository.save(p);
            } else {
                throw new RuntimeException("Product is out of stock: " + item.getProductId());
            }
        });
        order.setOrderStatus(OrderStatus.SUCCESS);
        return order;
    }

    @Override
    public Order revertOrder(Order order) {
        order.getLineItems()
                .forEach(l -> {
                    Product p = productRepository.findById(l.getProductId())
                            .orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
                    p.setStock(p.getStock() + l.getQuantity());
                    productRepository.save(p);
                });
        order.setOrderStatus(OrderStatus.SUCCESS);
        return order;
    }
}
