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
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        Product product = Product.builder()
                .name("Product"+rnd.nextInt(0,10))
                .stock(rnd.nextInt(1,100))
                .price(rnd.nextInt(1, 100))
                .build();
        productRepo.save(product);
        log.info("Preloaded product: {}", productRepo.findById(product.getId()));
    }

}