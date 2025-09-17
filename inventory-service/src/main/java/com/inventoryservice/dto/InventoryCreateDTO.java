package com.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryCreateDTO {

    @NotNull(message = "ID is mandatory")
    private Long id;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity must be zero or greater")
    private Integer quantity;

}
