package com.uade.order.infrastructure.adapter.in.messaging;

import com.uade.order.domain.event.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class KafkaProductCreatedEventListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaProductCreatedEventListener.class);

    @KafkaListener(
            topics = "product-created",
            groupId = "${spring.kafka.consumer.group-id:order-service-group}"
    )
    public void handleProductCreated(ProductCreatedEvent event) {
        log.info("=== EVENTO CONSUMIDO EN ORDER-SERVICE (Kafka) ===");
        log.info("Nuevo producto registrado en catálogo:");
        log.info("  ID:       {}", event.getProductId());
        log.info("  Nombre:   {}", event.getName());
        log.info("  Precio:   ${}", event.getPrice());
        log.info("  Fecha:    {}", event.getTimestamp());
        log.info("================================================");
    }
}
