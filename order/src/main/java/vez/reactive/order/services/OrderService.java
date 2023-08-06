package vez.reactive.order.services;

import vez.common.domain.order.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> getOrders();
}
