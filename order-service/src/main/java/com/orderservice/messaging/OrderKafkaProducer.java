package com.orderservice.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.event.OrderCancelledEvent;
import com.orderservice.event.OrderConfirmedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publishOrderconfirmedEvent(OrderConfirmedEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order.order_confirmed", event.getOrderId(), payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void publishOrderCancelledEvent(OrderCancelledEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order.order_cancelled", event.getOrderId(), payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
