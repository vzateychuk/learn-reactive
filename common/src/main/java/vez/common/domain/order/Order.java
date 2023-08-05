package vez.common.domain.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import vez.common.domain.Address;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "order")
public class Order {

    @Id private ObjectId objectId;

    private LocalDate shippingDate;
    private Address shipmentAddress;
    private OrderStatus orderStatus;
    private List<LineItem> lineItems;
}
