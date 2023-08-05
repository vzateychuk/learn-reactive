package vez.reactive.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vez.common.dto.order.Order;
import vez.common.dto.Product;
import vez.reactive.inventory.services.ProductService;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private final ProductService productService;

    @Autowired
    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/products", "/products/"})
    public Iterable<Product> getAllProducts() {
        return productService.getProducts();
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
