package org.authetication.ecommerce.entity.product;

import jakarta.validation.constraints.NotBlank;

public record AddProductRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        String brand,

        @NotBlank(message = "Price is required")
        Double price,
        
        String status,
        String imageUrl,
        String[] tags,
        String[] categories,

        @NotBlank(message = "Stock quantity is required")
        int stockQuantity,
        
        Double length,
        Double width,
        Double height

) {

}
