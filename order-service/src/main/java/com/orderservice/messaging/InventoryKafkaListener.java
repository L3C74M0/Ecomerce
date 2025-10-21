package com.orderservice.messaging;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderStatus;
import com.orderservice.event.OrderCancelledEvent;
import com.orderservice.event.OrderConfirmedEvent;
import com.orderservice.event.StockReleasedEvent;
import com.orderservice.event.StockReservedEvent;
import com.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryKafkaListener {

    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;
    private final OrderKafkaProducer orderKafkaProducer;

    @KafkaListener(topics = "inventory.stock_reserved", groupId = "order-group")
    public void consumeStockReserved(String message) {
        try {
            StockReservedEvent event = objectMapper.readValue(message, StockReservedEvent.class);
            Long orderId = event.getOrderId();
            String message_kafka = event.getMessage();

            Optional<Order> orderOptional = orderRepository.findById(orderId);

            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();

                if (order.getStatus() == OrderStatus.PENDING) {
                    order.setStatus(OrderStatus.CONFIRMED);
                    orderRepository.save(order);
                    orderKafkaProducer.publishOrderconfirmedEvent(new OrderConfirmedEvent(orderId, message_kafka));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "inventory.stock_released", groupId = "order-group")
    public void consumeStockReleased(String message) {
        try {
            StockReleasedEvent event = objectMapper.readValue(message, StockReleasedEvent.class);
            Long orderId = event.getOrderId();
            String reason = event.getReason();

            Optional<Order> orderOptional = orderRepository.findById(orderId);

            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();

                if (order.getStatus() == OrderStatus.PENDING) {
                    order.setStatus(OrderStatus.CANCELLED);
                    orderRepository.save(order);
                    orderKafkaProducer.publishOrderCancelledEvent(new OrderCancelledEvent(orderId, reason));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
