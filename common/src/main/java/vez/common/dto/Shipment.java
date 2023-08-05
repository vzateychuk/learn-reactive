package vez.common.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Shipment {

    private Address address;
    private LocalDate shipmentDate;
}
