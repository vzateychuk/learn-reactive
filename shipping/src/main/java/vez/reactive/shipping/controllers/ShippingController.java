package vez.reactive.shipping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vez.common.dto.order.Order;
import vez.reactive.shipping.services.ShippingService;

@RestController
@RequestMapping("/api/v1")
public class ShippingController {

    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/processOrder")
    public Order process(@RequestBody Order order) {
        return shippingService.handleOrder(order);
    }
}
