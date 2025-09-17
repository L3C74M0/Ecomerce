package com.inventoryservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDTO {

    private Long id;

    private Long productId;

    private Integer quantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
