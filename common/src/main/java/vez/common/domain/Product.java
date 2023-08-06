package vez.common.domain;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "product")
public class Product {

    private ObjectId id;
    private String name;
    private Integer price;
    private Integer stock;
}
