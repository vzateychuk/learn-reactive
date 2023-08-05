package vez.reactive.order.services;

import vez.common.domain.order.Order;

public interface OrderService {

    Order createOrder(Order order);

    Iterable<Order> getOrders();
}
