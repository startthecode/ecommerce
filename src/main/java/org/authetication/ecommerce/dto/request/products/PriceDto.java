package org.authetication.ecommerce.dto.request.products;

import jakarta.validation.constraints.NotNull;

public record PriceDto(
    @NotNull(message = "Price can not be null")
    Double price,

    @NotNull(message = "Compare Price can not be null")
    Double compare_at_price
) {

}
