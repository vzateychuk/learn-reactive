package vez.common.domain;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "product")
public class Product {

    @Id private ObjectId objectId;
    private Long productId;
    private Integer stock;
}
