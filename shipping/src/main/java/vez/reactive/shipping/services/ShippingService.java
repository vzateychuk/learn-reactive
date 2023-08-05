package vez.reactive.shipping.services;

import vez.common.dto.order.Order;

public interface ShippingService {

    Order handleOrder(Order order);

}
