package vez.common.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import vez.common.dto.Address;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {

    private Long orderId;
    private LocalDate shippingDate;
    private Address shipmentAddress;
    private OrderStatus orderStatus;
    private List<LineItem> lineItems;
}
