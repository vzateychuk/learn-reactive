package vez.common.domain.order;

import lombok.Data;

@Data
public class LineItem {
    private Long productId;
    private Integer quantity;
}
