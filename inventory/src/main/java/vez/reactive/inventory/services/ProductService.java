package vez.reactive.inventory.services;

import vez.common.domain.Product;
import vez.common.domain.order.Order;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Order handleOrder(Order order);

    Order revertOrder(Order order);

    Product getProductById(String productId);
}
