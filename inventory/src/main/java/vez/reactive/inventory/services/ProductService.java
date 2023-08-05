package vez.reactive.inventory.services;

import vez.reactive.inventory.dto.Order;
import vez.reactive.inventory.dto.Product;

public interface ProductService {

    Iterable<Product> getProducts();

    Order handleOrder(Order order);

    Order revertOrder(Order order);
}
