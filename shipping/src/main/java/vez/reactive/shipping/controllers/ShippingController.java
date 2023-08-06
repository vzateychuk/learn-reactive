package vez.reactive.shipping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vez.common.domain.Shipment;
import vez.common.domain.order.Order;
import vez.reactive.shipping.services.ShippingService;

import java.util.List;

@RestController
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

    @GetMapping({"/shipments", "/shipments/"})
    public List<Shipment> getAllShipments() {
        return shippingService.getAllShipments();
    }

}
