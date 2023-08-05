package vez.reactive.inventory.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import vez.common.domain.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product, Long> {
}
