package com.uade.order.domain.port.in;

import com.uade.order.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderUseCase {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
}
