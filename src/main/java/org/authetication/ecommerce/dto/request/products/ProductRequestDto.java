package org.authetication.ecommerce.dto.request.products;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.authetication.ecommerce.enums.Status;

public record ProductRequestDto(@NotBlank(message = "Title can not be blank") String title,

                                @NotBlank(message = "description can not be blank") String description,

                                String short_description,

                                @NotNull(message = "stock_quantity can not be blank") int stock_quantity,

                                @NotNull(message = "stock_quantity can not be blank") float length,

                                @NotNull(message = "stock_quantity can not be blank") float width,

                                @NotNull(message = "stock_quantity can not be blank") float height,

                                Status status,
                                String brand,
                                @Valid
                                ImagesDto images,

                                @Valid
                                PriceDto price,
                                Long comparePrice,
                                String[] tags,
                                String[] categories) {
    public ProductRequestDto {
        if (short_description == null) {
            short_description = description();
        }
    }
}
