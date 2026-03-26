package org.authetication.ecommerce.dto.request.products;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.authetication.ecommerce.enums.Status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record ProductUpdateReqDto(
        String title,
        String description,

        String short_description,


        Integer stock_quantity,


        Double length,


        Double width,


        Double height,

        // Allow null status if optional
        Status status,


        String brand,

        ImagesDto images,
        PriceDto price,


        String[] tags,


        String[] categories
) {}
