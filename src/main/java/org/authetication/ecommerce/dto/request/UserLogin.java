package org.authetication.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLogin(
    @NotBlank(message = "username name can not be blank")
        String username,
    @NotBlank(message = "password name can not be blank")
        String password
) {

}
