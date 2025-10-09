package com.inventoryservice.event;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateEvent {

    private String orderId;
    private List<OrderItem> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem {
        private Long productId;
        private Integer quantity;
    }
}
