package com.inventoryservice.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryservice.event.OrderCreateEvent;
import com.inventoryservice.event.StockReleasedEvent;
import com.inventoryservice.event.StockReservedEvent;
import com.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryKafkaListener {

    private final InventoryService inventoryService;
    private final InventoryKafkaProducer inventoryKafkaProducer;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order.order_created", groupId = "inventory-group")
    public void consumeOrderCreated(String message) {
        try {
            OrderCreateEvent event = objectMapper.readValue(message, OrderCreateEvent.class);
            
            boolean allAvaliable = true;

            for (OrderCreateEvent.OrderItem item : event.getItems()) {
                if(!inventoryService.hasSufficientStock(item.getProductId(), item.getQuantity())) {
                    allAvaliable = false;
                    break;
                }
            }

            if (allAvaliable) {
                for (OrderCreateEvent.OrderItem item : event.getItems()) {
                    inventoryService.UpdateStock(item.getProductId(), item.getQuantity());
                }
                
                inventoryKafkaProducer.publishStockReserved(new StockReservedEvent(event.getOrderId(), "Stock reserved"));
            } else {
                inventoryKafkaProducer.publishStockReleased(new StockReleasedEvent(event.getOrderId(), "Insufficient stock"));
            }

        } catch (Exception e) {
            
        }
    }

}
