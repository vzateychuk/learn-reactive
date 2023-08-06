package vez.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import vez.common.domain.Product;

import vez.reactive.inventory.repo.ProductRepo;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableMongoRepositories
public class InventoryApp implements CommandLineRunner {

    @Autowired  private ProductRepo productRepo;

    public static void main(String[] args) {
        SpringApplication.run(InventoryApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Random rnd = ThreadLocalRandom.current();
        Product doc = Product.builder()
                .name("Product"+rnd.nextInt(0,10))
                .stock(rnd.nextInt(1,100))
                .price(rnd.nextInt(1, 100))
                .build();
        productRepo.save(doc);
    }
}