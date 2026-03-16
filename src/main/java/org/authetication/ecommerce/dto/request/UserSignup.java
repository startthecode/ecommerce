package org.authetication.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserSignup(
        @NotBlank(message = "username name can not be blank")
        String username,

        @NotBlank(message = "password name can not be blank")
        @Length(min = 5,message = "password should contains 5 letter")
                String password,

        @NotBlank(message = "email name can not be blank")
        @Email(message = "email should be valid")
        String email,
        
        @NotBlank(message = "name can not be blank")
        String name

) {
}
