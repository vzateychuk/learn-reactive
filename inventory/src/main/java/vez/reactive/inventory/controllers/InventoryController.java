package vez.reactive.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vez.common.domain.Product;
import vez.common.domain.order.Order;
import vez.reactive.inventory.services.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class InventoryController {

    private final ProductService productService;

    @Autowired
    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/products"})
    public Flux<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping({"/products/{productId}"})
    public Mono<Product> getProduct(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/processOrder")
    public Mono<Order> processOrder(@RequestBody Order order) {
        return productService.handleOrder(order);
    }

    @DeleteMapping("/revertOrder")
    public Mono<Order> revertOrder(@RequestBody Order order) {
        return productService.revertOrder(order);
    }
}
