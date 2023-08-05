package vez.reactive.order.services;

import vez.common.dto.order.Order;

public interface OrderService {

    Order createOrder(Order order);

    Iterable<Order> getOrders();
}
