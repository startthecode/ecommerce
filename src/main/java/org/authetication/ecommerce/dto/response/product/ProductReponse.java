package org.authetication.ecommerce.dto.response.product;

import org.authetication.ecommerce.dto.request.products.PriceDto;

public record ProductReponse(
        String title,
        String description,
        String brand,
        PriceDto price,
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
