package org.authetication.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserDTLCreate (
        @NotBlank(message = "address can not be blank")
        @Length(max = 200,min =10,message = "200 and 10")
        String address,

        @NotNull(message = "Pincode cannot be null")
        Long pincode
){
}
