package vez.reactive.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vez.reactive.inventory.repo.ProductRepo;

@SpringBootApplication
public class InventoryApp {

    @Autowired  private ProductRepo productRepo;

    public static void main(String[] args) {
        SpringApplication.run(InventoryApp.class, args);
    }

/*
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
*/
}