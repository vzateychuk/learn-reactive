package vez.reactive.shipping.services;

import vez.common.domain.order.Order;

public interface ShippingService {

    Order handleOrder(Order order);

}
