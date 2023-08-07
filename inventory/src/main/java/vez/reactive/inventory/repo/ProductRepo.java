package vez.reactive.inventory.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import vez.common.domain.Product;

@Repository
public interface ProductRepo extends ReactiveMongoRepository<Product, ObjectId> {
}
