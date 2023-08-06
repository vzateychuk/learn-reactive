package vez.reactive.order.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vez.common.domain.order.Order;
import vez.common.domain.order.OrderStatus;
import vez.reactive.order.clients.InventoryClient;
import vez.reactive.order.clients.ShipmentClient;
import vez.reactive.order.repo.OrderRepo;

import java.util.List;

@Slf4j
@Service
public class OrderServiceSync implements OrderService {

    private final OrderRepo orderRepository;

    private final InventoryClient inventoryClient;
    private final ShipmentClient shipmentClient;

    @Autowired
    public OrderServiceSync(OrderRepo orderRepository, InventoryClient inventoryClient, ShipmentClient shipmentClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.shipmentClient = shipmentClient;
    }

    @Override
    public Order createOrder(Order order) {

        log.debug("saving order: {}", order);
        Order savedOrder = orderRepository.save(order);

        // trying to save inventory & shipment
        try {
            Order invResp = inventoryClient.processOrder(order);
            Order shipResp = shipmentClient.processOrder(order);

            boolean success = OrderStatus.SUCCESS.equals( invResp.getOrderStatus() ) &&
                              OrderStatus.SUCCESS.equals( shipResp.getOrderStatus() );
            if ( success ) {
                savedOrder.setOrderStatus(OrderStatus.SUCCESS);
                savedOrder.setShippingDate(shipResp.getShippingDate());
            } else {
                savedOrder.setOrderStatus(OrderStatus.FAILURE);
            }
            log.debug("Save status:\ninventory: {};\nshipment: {};\norder: {}",
                    invResp.getOrderStatus(), shipResp.getOrderStatus(), savedOrder.getOrderStatus());

        } catch (Exception ex) {
            log.error("exception when process inventory and shipping", ex);
            savedOrder.setOrderStatus(OrderStatus.FAILURE);
        }

        return orderRepository.save(savedOrder);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
