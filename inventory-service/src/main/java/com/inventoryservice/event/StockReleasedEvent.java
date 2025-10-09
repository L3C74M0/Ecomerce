package com.inventoryservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockReleasedEvent {
    private String orderId;
    private String reason;
}
