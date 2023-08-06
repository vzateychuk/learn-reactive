package vez.reactive.order.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vez.common.domain.order.Order;
import vez.common.domain.order.OrderStatus;
import vez.reactive.order.services.OrderService;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        Order processedOrder = orderService.createOrder(order);

        if (OrderStatus.FAILURE.equals(processedOrder.getOrderStatus())) {
            throw new RuntimeException("Order processing failed, please try again later.");
        }
        return processedOrder;
    }

    @GetMapping({"/orders"})
    public List<Order> getAll() {
        return orderService.getOrders();
    }
}
