package org.authetication.ecommerce.Mapping;

import org.authetication.ecommerce.dto.response.ApiBaseResponse;

public final class ResponseMapper {

    private ResponseMapper() {
        // utility class
    }

    public static <T> ApiBaseResponse<T> success(T data, String message) {
        return new ApiBaseResponse<>(true, message, data);
    }
}
