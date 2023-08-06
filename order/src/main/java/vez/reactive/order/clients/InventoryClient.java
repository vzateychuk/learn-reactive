package vez.reactive.order.clients;

import vez.common.domain.order.Order;

public interface InventoryClient {
    Order processOrder(Order order);
}
