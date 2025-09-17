package com.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryUpdateDTO {

    @NotNull(message = "ID is mandatory")
    @Min(value = 0, message = "ID must be greater than 0")
    private Integer quantity;

}
