package org.authetication.ecommerce.dto.request.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record AddToCartReqDto(
        @Min(1)
int quantity
) {

}
