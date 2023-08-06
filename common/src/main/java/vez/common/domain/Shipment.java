package vez.common.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import vez.common.serializer.ObjectIdSerializer;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "shipment")
public class Shipment {

    @Id @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;
    private Address address;
    private LocalDate shipmentDate;
}
