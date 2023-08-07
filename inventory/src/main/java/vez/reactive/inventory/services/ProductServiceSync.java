package vez.reactive.inventory.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vez.common.domain.Product;
import vez.common.domain.order.LineItem;
import vez.common.domain.order.Order;
import vez.common.domain.order.OrderStatus;
import vez.reactive.inventory.exceptions.OutOfStockException;
import vez.reactive.inventory.repo.ProductRepo;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceSync implements ProductService{

    private final ProductRepo productRepository;

    @Autowired
    public ProductServiceSync(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> getProductById(String productId) {
        return productRepository.findById( new ObjectId(productId) );
    }

    @Override
    @Transactional
    public Mono<Order> handleOrder(Order order) {

        // Map { ProductID : OrderQuantity }
        Map<ObjectId, Integer> prodQuantityOrder = order.getLineItems().stream()
                .collect(
                        Collectors.toMap(
                                LineItem::getProductId,
                                LineItem::getQuantity,
                                Integer::sum
                        )
                );

        return Flux.fromIterable(prodQuantityOrder.keySet())
                .flatMap(productRepository::findById)
                .flatMap(p -> {
                    int orderQuantity = prodQuantityOrder.get(p.getId());
                    if (p.getStock() >= orderQuantity) {
                        p.setStock(p.getStock() - orderQuantity);
                        return productRepository.save(p);
                    } else {
                        return Mono.error(new OutOfStockException(p));
                    }
                })
                .then(
                        Mono.fromCallable(() -> {
                            order.setOrderStatus(OrderStatus.SUCCESS);
                            return order;
                        })
                );
    }

    @Override
    public Mono<Order> revertOrder(Order order) {

        // Map { ProductID : OrderQuantity }
        Map<ObjectId, Integer> prodQuantityOrder = order.getLineItems().stream()
                .collect(
                        Collectors.toMap(
                                LineItem::getProductId,
                                LineItem::getQuantity,
                                Integer::sum
                        )
                );

        return Flux.fromIterable(prodQuantityOrder.keySet())
                .flatMap(productRepository::findById)
                .flatMap(p -> {
                    int orderQuantity = prodQuantityOrder.get(p.getId());
                    p.setStock(p.getStock() + orderQuantity);
                    return productRepository.save(p);
                })
                .then(
                        Mono.fromCallable(() -> {
                            order.setOrderStatus(OrderStatus.REVERTED);
                            return order;
                        })
                );
    }
}
