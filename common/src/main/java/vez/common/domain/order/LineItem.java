package vez.common.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {

    private ObjectId productId;
    private Integer quantity;
}
