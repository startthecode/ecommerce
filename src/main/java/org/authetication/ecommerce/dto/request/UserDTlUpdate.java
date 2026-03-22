package org.authetication.ecommerce.dto.request;


import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserDTlUpdate(
        @Length(max = 200,min =10,message = "200 and 10")
        String address,

         Long pincode
) {
}
