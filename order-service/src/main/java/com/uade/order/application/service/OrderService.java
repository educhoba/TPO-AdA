package com.uade.order.application.service;

import com.uade.order.domain.event.OrderCreatedEvent;
import com.uade.order.domain.model.Order;
import com.uade.order.domain.port.in.OrderUseCase;
import com.uade.order.domain.port.out.EventPublisherPort;
import com.uade.order.domain.port.out.OrderRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderUseCase {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepositoryPort orderRepositoryPort;
    private final EventPublisherPort eventPublisherPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort, EventPublisherPort eventPublisherPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public Order createOrder(Order order) {
        log.info("Creando pedido para el producto: {}", order.getProductName());
        order.setStatus("PENDING");
        Order savedOrder = orderRepositoryPort.save(order);
        
        // Publicar evento de orden creada
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getProductName(),
                savedOrder.getQuantity(),
                savedOrder.getPrice()
        );
        eventPublisherPort.publishOrderCreated(event);
        
        return savedOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        log.info("Obteniendo todos los pedidos");
        return orderRepositoryPort.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        log.info("Obteniendo pedido por id: {}", id);
        return orderRepositoryPort.findById(id);
    }
}
