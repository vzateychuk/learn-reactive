package vez.reactive.shipping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vez.common.domain.Shipment;
import vez.common.domain.order.Order;
import vez.common.domain.order.OrderStatus;
import vez.reactive.shipping.repo.ShippingRepo;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class ShippingServiceSync implements ShippingService {

    private static final LocalTime WORKDAY_START = LocalTime.parse("10:00");
    private static final LocalTime WORKDAY_END = LocalTime.parse("18:00");
    private final ShippingRepo shipmentRepository;

    @Autowired
    public ShippingServiceSync(ShippingRepo shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Order handleOrder(Order order) {

        LocalTime now = LocalTime.now();
        LocalDate shippingDate;

        if (now.isAfter(WORKDAY_START) && now.isBefore(WORKDAY_END)) {
            shippingDate = LocalDate.now().plusDays(1);
        } else {
            throw new RuntimeException("The current time is off the limits to place order.");
        }
        // save Shipment
        shipmentRepository.save(
                Shipment.builder()
                        .address(order.getShipmentAddress())
                        .shipmentDate(shippingDate)
                        .build()
        );
        // return processed order
        order.setShippingDate(shippingDate);
        order.setOrderStatus(OrderStatus.SUCCESS);
        return order;
    }}
