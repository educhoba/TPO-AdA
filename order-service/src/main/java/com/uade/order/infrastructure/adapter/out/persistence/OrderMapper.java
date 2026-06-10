package com.uade.order.infrastructure.adapter.out.persistence;

import com.uade.order.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toDomain(OrderJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Order(
                entity.getId(),
                entity.getProductName(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getStatus()
        );
    }

    public OrderJpaEntity toEntity(Order domain) {
        if (domain == null) {
            return null;
        }
        return new OrderJpaEntity(
                domain.getId(),
                domain.getProductName(),
                domain.getQuantity(),
                domain.getPrice(),
                domain.getStatus()
        );
    }
}
