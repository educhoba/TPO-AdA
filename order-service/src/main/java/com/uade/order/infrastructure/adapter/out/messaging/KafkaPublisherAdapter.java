package com.uade.order.infrastructure.adapter.out.messaging;

import com.uade.order.domain.event.OrderCreatedEvent;
import com.uade.order.domain.port.out.EventPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class KafkaPublisherAdapter implements EventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(KafkaPublisherAdapter.class);

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public KafkaPublisherAdapter(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishOrderCreated(OrderCreatedEvent event) {
        kafkaTemplate.send(KafkaConfig.ORDER_CREATED_TOPIC, String.valueOf(event.getOrderId()), event);
        log.info("Evento publicado en Kafka: OrderCreated [id={}, productName={}]",
                event.getOrderId(), event.getProductName());
    }
}
