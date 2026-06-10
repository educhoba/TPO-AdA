package com.uade.order.infrastructure.adapter.in.messaging;

import com.uade.order.domain.event.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("rabbitmq")
public class ProductCreatedEventListener {

    private static final Logger log = LoggerFactory.getLogger(ProductCreatedEventListener.class);

    @RabbitListener(queues = "order.product.created.queue")
    public void handleProductCreated(ProductCreatedEvent event) {
        log.info("=== EVENTO CONSUMIDO EN ORDER-SERVICE ===");
        log.info("Nuevo producto registrado en catálogo:");
        log.info("  ID:       {}", event.getProductId());
        log.info("  Nombre:   {}", event.getName());
        log.info("  Precio:   ${}", event.getPrice());
        log.info("  Fecha:    {}", event.getTimestamp());
        log.info("=========================================");
    }
}
