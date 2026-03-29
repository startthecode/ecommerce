package org.authetication.ecommerce.dto.request.cart;

import jakarta.validation.constraints.Min;

public record UpdateCartReqDto(
        @Min(1)
        int quantity
) {
}
