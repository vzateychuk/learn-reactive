package vez.reactive.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import vez.common.dto.order.Order;
import vez.common.dto.order.OrderStatus;
import vez.reactive.order.repo.OrderRepo;

@Service
public class OrderServiceSync implements OrderService {

    private final OrderRepo orderRepository;
    private final RestOperations restTemplate;

    @Value("${inventoryServiceUrl}") private String inventoryServiceUrl;
    @Value("${shippingServiceUrl}") private String shippingServiceUrl;

    @Autowired
    public OrderServiceSync(OrderRepo orderRepository, RestOperations restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Order createOrder(Order order) {

        boolean success = true;

        Order savedOrder = orderRepository.save(order);

        // trying to save inventory
        try {
            restTemplate.postForObject(inventoryServiceUrl, order, Order.class);
        } catch (Exception ex) {
            success = false;
        }

        // trying to save shipment
        Order shippingResponse = null;
        try {
            shippingResponse = restTemplate.postForObject(shippingServiceUrl, order, Order.class);
        } catch (Exception ex) {
            success = false;
            HttpEntity<Order> deleteRequest = new HttpEntity<>(order);
            restTemplate.exchange(
                    inventoryServiceUrl, HttpMethod.DELETE, deleteRequest, Order.class
            );
        }

        // finally save/set order status
        if (success) {
            savedOrder.setOrderStatus(OrderStatus.SUCCESS);
            savedOrder.setShippingDate(shippingResponse.getShippingDate());
        } else {
            savedOrder.setOrderStatus(OrderStatus.FAILURE);
        }
        return orderRepository.save(savedOrder);
    }

    @Override
    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }
}
