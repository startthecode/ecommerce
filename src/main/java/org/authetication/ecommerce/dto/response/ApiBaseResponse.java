package org.authetication.ecommerce.dto.response;

public record ApiBaseResponse<T>(
        boolean status,
        String message,
        T data
) {
}
