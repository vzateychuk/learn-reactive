package vez.reactive.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vez.reactive.order.repo.OrderRepo;

@SpringBootApplication
public class OrderApp {

    @Autowired private OrderRepo orderRepo;

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }

/*
    @Override
    public void run(String... args) throws Exception {
        int quantity = ThreadLocalRandom.current().nextInt(1, 10);
        LineItem lineItem = new LineItem(new ObjectId("64cfe2bfc35d2a67041d5947"), quantity);
        Address address = new Address("Минск, Червякова 23");

        Order order = new Order();
        order.setOrderStatus(OrderStatus.CREATED);
        order.setLineItems(Collections.singletonList(lineItem));
        order.setShipmentAddress(address);

        orderRepo.save(order);
    }
*/
}