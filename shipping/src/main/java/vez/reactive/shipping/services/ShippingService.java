package vez.reactive.shipping.services;

import vez.common.domain.Shipment;
import vez.common.domain.order.Order;

import java.util.List;

public interface ShippingService {

    Order handleOrder(Order order);

    List<Shipment> getAllShipments();
}
