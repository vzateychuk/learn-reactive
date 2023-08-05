package vez.reactive.inventory.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vez.reactive.inventory.dto.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
}
