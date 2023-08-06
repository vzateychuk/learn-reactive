package vez.common.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import vez.common.domain.Address;

import java.time.LocalDate;
import java.util.List;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
public class Order {

    @Id private ObjectId id;
    private LocalDate shippingDate;
    private Address shipmentAddress;
    private OrderStatus orderStatus;
    private List<LineItem> lineItems;
}
