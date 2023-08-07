package vez.reactive.inventory.exceptions;

import vez.common.domain.Product;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(Product p) {
        super("Product is out of stock: " + p.getId());
    }
}
