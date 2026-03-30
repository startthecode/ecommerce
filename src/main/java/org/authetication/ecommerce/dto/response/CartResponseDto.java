package org.authetication.ecommerce.dto.response;

import java.util.Set;

public record CartResponseDto(
        Long totalAmount,
        Set<Object> cartItems
) {
}
