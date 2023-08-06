package vez.reactive.order.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import vez.common.domain.order.Order;

@Slf4j
@Service
public class InventoryClientSync implements InventoryClient {

    @Value("${client.inventory.baseUrl}") private String inventoryBaseUrl;

    private final RestOperations restTemplate;

    @Autowired
    public InventoryClientSync(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Order processOrder(Order order) {

        log.debug("inventory processing order: {}", order);

        String inventoryServiceUrl = inventoryBaseUrl + "/processOrder";
        try {
            return restTemplate.postForObject(inventoryServiceUrl, order, Order.class);
        } catch (Exception ex) {
            log.error("error when process inventory", ex);
            throw new RuntimeException("error when process inventory");
        }
    }
}
