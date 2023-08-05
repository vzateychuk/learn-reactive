package vez.common.domain;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "shipment")
public class Shipment {

    @Id private ObjectId objectId;
    private Address address;
    private LocalDate shipmentDate;
}
