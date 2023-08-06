package vez.reactive.order.clients;

import vez.common.domain.order.Order;

public interface ShipmentClient {
    Order processOrder(Order order);
}
