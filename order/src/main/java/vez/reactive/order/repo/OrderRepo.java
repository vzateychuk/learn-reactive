package vez.reactive.order.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vez.common.domain.order.Order;

@Repository
public interface OrderRepo extends MongoRepository<Order, Long> {
}
