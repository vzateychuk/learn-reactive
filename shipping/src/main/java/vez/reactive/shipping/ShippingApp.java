package vez.reactive.shipping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vez.common.domain.Address;
import vez.common.domain.Shipment;
import vez.reactive.shipping.repo.ShippingRepo;

import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class ShippingApp implements CommandLineRunner {

    @Autowired private ShippingRepo shipmentRepo;

    public static void main(String[] args) {
        SpringApplication.run(ShippingApp.class, args);
    }

    @Override
    public void run(String... args) {
        Shipment shipment = Shipment.builder()
                .shipmentDate(LocalDate.now())
                .address(new Address("preloaded"))
                        .build();
        shipmentRepo.save(shipment);
        log.info("Preloaded shipment: {}", shipmentRepo.findById(shipment.getId()));
    }
}