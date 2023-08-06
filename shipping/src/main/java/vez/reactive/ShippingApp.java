package vez.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vez.common.domain.Address;
import vez.common.domain.Shipment;
import vez.reactive.shipping.repo.ShippingRepo;

import java.time.LocalDate;

@SpringBootApplication
public class ShippingApp implements CommandLineRunner {

    @Autowired private ShippingRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(ShippingApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = Address.builder().address("Chelyabinsk").build();
        Shipment ship = Shipment.builder()
                .shipmentDate(LocalDate.now())
                .address(address)
                .build();
        repo.save(ship);
    }
}