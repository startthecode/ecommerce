package org.authetication.ecommerce.dto.request.auth;

import jakarta.validation.constraints.NotBlank;

public record UserSigninDto(
    @NotBlank(message = "username name can not be blank")
    String username,
    @NotBlank(message = "password name can not be blank")
    String password
) {

}
