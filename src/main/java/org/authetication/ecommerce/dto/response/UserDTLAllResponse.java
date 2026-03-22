package org.authetication.ecommerce.dto.response;

import org.authetication.ecommerce.entity.user.UserDtlEntity;
import org.authetication.ecommerce.entity.user.UserEntity;

public record UserDTLAllResponse(

        Long userid,
        String name,
        String username,
        String email,
        String role,

        String address,
        Long pincode

) {}
