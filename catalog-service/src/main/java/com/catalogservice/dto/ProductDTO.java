package com.catalogservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {

    @NotNull(message = "Id is mandatory")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "The name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "The price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Status is mandatory")
    private String status;

    @NotNull(message = "Stock is mandatory")
    @Min(value = 0, message = "Stock must be zero or greater")
    private Integer stock;

    private boolean active;
}
    