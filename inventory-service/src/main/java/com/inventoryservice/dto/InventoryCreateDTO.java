package com.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCreateDTO {

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @Min(value = 0, message = "Quantity must be zero or greater")
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

}
