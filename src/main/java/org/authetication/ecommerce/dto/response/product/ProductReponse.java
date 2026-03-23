package org.authetication.ecommerce.dto.response.product;


public record ProductReponse(
        String title,
        String description,
        String brand,
        Double price,
        Double compare_at_price,
        String status,
        Object imageUrl,
        String[] tags,
        String[] categories,
        int stockQuantity,
        Double length,
        Double width,
        Double height
) {

}
