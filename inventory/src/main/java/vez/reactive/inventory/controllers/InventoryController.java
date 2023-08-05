package vez.reactive.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vez.reactive.inventory.dto.Order;
import vez.reactive.inventory.dto.Product;
import vez.reactive.inventory.services.ProductService;

@RestController
public class InventoryController {

    private final ProductService productService;

    @Autowired
    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public Order processOrder(@RequestBody Order order) {
        return productService.handleOrder(order);
    }

    @DeleteMapping
    public Order revertOrder(@RequestBody Order order) {
        return productService.revertOrder(order);
    }
}
