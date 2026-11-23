package com.shopsphere.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product name is required")
        @Size(max = 150)
        String name,

        @Size(max = 2000)
        String description,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal price,

        @NotNull
        @Min(0)
        Integer stockQuantity

) {
}
