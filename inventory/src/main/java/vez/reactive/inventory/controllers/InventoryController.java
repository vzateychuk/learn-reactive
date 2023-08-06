package vez.reactive.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vez.common.domain.Product;
import vez.common.domain.order.Order;
import vez.reactive.inventory.services.ProductService;

import java.util.List;

@RestController
public class InventoryController {

    private final ProductService productService;

    @Autowired
    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/products"})
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping({"/products/{productId}"})
    public Product getProduct(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/processOrder")
    public Order processOrder(@RequestBody Order order) {
        return productService.handleOrder(order);
    }

    @DeleteMapping("/revertOrder")
    public Order revertOrder(@RequestBody Order order) {
        return productService.revertOrder(order);
    }
}
