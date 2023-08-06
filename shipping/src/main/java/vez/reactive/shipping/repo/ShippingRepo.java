package vez.reactive.shipping.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vez.common.domain.Shipment;

@Repository
public interface ShippingRepo extends MongoRepository<Shipment, ObjectId> {
}
