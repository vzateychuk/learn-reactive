package vez.reactive.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vez.common.domain.Product;
import vez.reactive.inventory.repo.ProductRepo;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@SpringBootApplication
public class InventoryApp implements CommandLineRunner {

    @Autowired  private ProductRepo productRepo;

    public static void main(String[] args) {
        SpringApplication.run(InventoryApp.class, args);
    }

    @Override
    public void run(String... args) {
        Product product = Product.builder()
                .name("Product"+ThreadLocalRandom.current().nextInt(1,10))
                .stock(ThreadLocalRandom.current().nextInt(1,100))
                .price(ThreadLocalRandom.current().nextInt(1, 100))
                .build();
        productRepo.save(product)
                .flatMap(p -> productRepo.findById(p.getId()))
                .subscribe(p -> log.info("Preloaded product: {}", p.getId()));
    }

}