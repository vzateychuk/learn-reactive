package vez.common.dto.order;

import lombok.Data;

@Data
public class LineItem {
    private Long productId;
    private Integer quantity;
}
