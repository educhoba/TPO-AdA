package com.uade.notification.listener;

import com.uade.notification.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class KafkaOrderEventListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaOrderEventListener.class);

    @KafkaListener(
            topics = "order-created",
            groupId = "notification-group",
            containerFactory = "orderKafkaListenerContainerFactory"
    )
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("=== NOTIFICACIÓN DE PEDIDO RECIBIDA (Kafka) ===");
        log.info("Nuevo pedido procesado:");
        log.info("  Pedido ID:  {}", event.getOrderId());
        log.info("  Producto:   {}", event.getProductName());
        log.info("  Cantidad:   {}", event.getQuantity());
        log.info("  Precio:     ${}", event.getPrice());
        log.info("  Fecha:      {}", event.getTimestamp());
        log.info("=============================================");
    }
}
