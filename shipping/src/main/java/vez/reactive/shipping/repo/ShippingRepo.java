package vez.reactive.shipping.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vez.common.dto.Shipment;

@Repository
public interface ShippingRepo extends CrudRepository<Shipment, Long> {
}
