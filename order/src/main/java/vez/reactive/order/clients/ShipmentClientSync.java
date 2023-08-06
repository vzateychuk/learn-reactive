package vez.reactive.order.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import vez.common.domain.order.Order;

@Slf4j
@Service
public class ShipmentClientSync implements ShipmentClient {

    @Value("${client.shipment.baseUrl}") private String shippingBaseUrl;

    private final RestOperations restTemplate;

    @Autowired
    public ShipmentClientSync(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Order processOrder(Order order) {

        log.debug("inventory processing order: {}", order);

        String shippingServiceUrl = shippingBaseUrl + "/processOrder";
        try {
            return restTemplate.postForObject(shippingServiceUrl, order, Order.class);
        } catch (Exception ex) {
            log.error("error when process shipment", ex);
            throw new RuntimeException("error when process shipment", ex);
        }

    }
}
