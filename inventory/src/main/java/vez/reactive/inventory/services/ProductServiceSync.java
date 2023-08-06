package vez.reactive.inventory.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vez.common.domain.Product;
import vez.common.domain.order.Order;
import vez.common.domain.order.OrderStatus;
import vez.reactive.inventory.repo.ProductRepo;

import java.util.List;

@Service
public class ProductServiceSync implements ProductService{

    private final ProductRepo productRepository;

    @Autowired
    public ProductServiceSync(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String productId) {

        ObjectId objectId = new ObjectId(productId);
        return productRepository.findById(objectId)
                .orElseThrow(
                        () -> new RuntimeException("Could not find the product: " + productId)
                );
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
