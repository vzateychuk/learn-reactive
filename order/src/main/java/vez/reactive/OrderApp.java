package vez.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vez.common.domain.Address;
import vez.common.domain.order.LineItem;
import vez.common.domain.order.Order;
import vez.common.domain.order.OrderStatus;
import vez.reactive.order.repo.OrderRepo;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class OrderApp implements CommandLineRunner {

    @Autowired private OrderRepo orderRepo;

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int quantity = ThreadLocalRandom.current().nextInt(1, 10);
        LineItem lineItem = new LineItem(1L, quantity);
        Address address = new Address("Минск, Червякова 23");

        Order order = new Order();
        order.setOrderStatus(OrderStatus.CREATED);
        order.setLineItems(Collections.singletonList(lineItem));
        order.setShipmentAddress(address);

        orderRepo.save(order);
    }
}