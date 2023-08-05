package vez.reactive.order.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vez.common.domain.order.Order;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
}
