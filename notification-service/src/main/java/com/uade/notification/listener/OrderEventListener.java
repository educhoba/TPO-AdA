package com.uade.notification.listener;

import com.uade.notification.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbitmq")
public class OrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);

    @RabbitListener(queues = "order.created.queue")
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("=== NOTIFICACIÓN DE PEDIDO RECIBIDA ===");
        log.info("Nuevo pedido procesado:");
        log.info("  Pedido ID:  {}", event.getOrderId());
        log.info("  Producto:   {}", event.getProductName());
        log.info("  Cantidad:   {}", event.getQuantity());
        log.info("  Precio:     ${}", event.getPrice());
        log.info("  Fecha:      {}", event.getTimestamp());
        log.info("=======================================");
    }
}
