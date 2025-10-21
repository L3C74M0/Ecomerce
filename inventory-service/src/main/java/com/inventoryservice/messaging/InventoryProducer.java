package com.inventoryservice.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryservice.event.StockReleasedEvent;
import com.inventoryservice.event.StockReservedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publishStockReserved(StockReservedEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("inventory.stock_reserved", event.getOrderId(), payload);
        } catch (JsonProcessingException e) {
              e.printStackTrace();
        }
    }

    public void publishStockReleased(StockReleasedEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("inventory.stock_released", event.getOrderId(), payload);
        } catch (JsonProcessingException e) {
              e.printStackTrace();
        }
    }

}
