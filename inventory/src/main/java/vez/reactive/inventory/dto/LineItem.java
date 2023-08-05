package vez.reactive.inventory.dto;

import lombok.Data;

@Data
public class LineItem {
    private Long productId;
    private Integer quantity;
}
