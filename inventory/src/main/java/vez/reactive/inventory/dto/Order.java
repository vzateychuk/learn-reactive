package vez.reactive.inventory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Order {

    private OrderStatus orderStatus;
    private List<LineItem> lineItems;
}
