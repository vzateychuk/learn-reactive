package vez.reactive.inventory.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vez.common.domain.Product;
import vez.common.domain.order.Order;

public interface ProductService {

    Flux<Product> getProducts();

    Mono<Order> handleOrder(Order order);

    Mono<Order> revertOrder(Order order);

    Mono<Product> getProductById(String productId);
}
