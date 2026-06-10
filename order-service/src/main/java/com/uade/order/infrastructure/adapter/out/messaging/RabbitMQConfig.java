package com.uade.order.infrastructure.adapter.out.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbitmq")
public class RabbitMQConfig {

    // Output: Order events
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_CREATED_QUEUE = "order.created.queue";
    public static final String ORDER_CREATED_ROUTING_KEY = "order.created";

    // Input: Product events (from inventory-service)
    public static final String INVENTORY_EXCHANGE = "inventory.exchange";
    public static final String PRODUCT_CREATED_IN_ORDER_QUEUE = "order.product.created.queue";
    public static final String PRODUCT_CREATED_ROUTING_KEY = "product.created";

    // Bean declarations for publishing Order events
    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable(ORDER_CREATED_QUEUE).build();
    }

    @Bean
    public Binding orderCreatedBinding(Queue orderCreatedQueue, TopicExchange orderExchange) {
        return BindingBuilder.bind(orderCreatedQueue).to(orderExchange).with(ORDER_CREATED_ROUTING_KEY);
    }

    // Bean declarations for consuming Product events
    @Bean
    public TopicExchange inventoryExchange() {
        return new TopicExchange(INVENTORY_EXCHANGE);
    }

    @Bean
    public Queue orderProductCreatedQueue() {
        return QueueBuilder.durable(PRODUCT_CREATED_IN_ORDER_QUEUE).build();
    }

    @Bean
    public Binding orderProductCreatedBinding(Queue orderProductCreatedQueue, TopicExchange inventoryExchange) {
        return BindingBuilder.bind(orderProductCreatedQueue).to(inventoryExchange).with(PRODUCT_CREATED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
