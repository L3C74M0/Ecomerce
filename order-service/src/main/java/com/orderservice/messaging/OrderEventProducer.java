package com.orderservice.messaging;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.orderservice.entity.Order;
import com.orderservice.event.OrderCreatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Value("${kafka.topics.orderCreated}")
    private String orderCreatedTopic;

    public void sendOrderCreatedEvent(Order order) {
        List<OrderCreatedEvent.OrderItem> items = order.getItems().stream()
        .map(item -> new OrderCreatedEvent.OrderItem(item.getProductId(), item.getQuantity()))
        .toList();

        OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), items);
        kafkaTemplate.send("order.order_created", order.getId().toString(), event);
    }

}
