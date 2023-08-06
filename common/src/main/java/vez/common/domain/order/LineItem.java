package vez.common.domain.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import vez.common.serializer.ObjectIdSerializer;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId productId;
    private Integer quantity;
}
