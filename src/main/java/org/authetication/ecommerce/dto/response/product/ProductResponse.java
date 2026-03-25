package org.authetication.ecommerce.dto.response.product;

import org.authetication.ecommerce.dto.request.products.ImagesDto;
import org.authetication.ecommerce.dto.request.products.PriceDto;

public record ProductResponse(

        Long productid,
        String title,
        String description,
        String brand,
        PriceDto price,
        String status,
        ImagesDto images,
        String[] tags,
        String[] categories,
        int stockQuantity,
        Double length,
        Double width,
        Double height
) {

}
