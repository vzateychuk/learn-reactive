package vez.reactive.inventory.services;

import vez.common.dto.order.Order;
import vez.common.dto.Product;

public interface ProductService {

    Iterable<Product> getProducts();

    Order handleOrder(Order order);

    Order revertOrder(Order order);
}
