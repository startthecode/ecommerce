package org.authetication.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;

public record ChangeRoleRequest(

        @NotNull(message = "User ID is required")
        Long userID,

        @NotNull(message = "Role ID is required")
        Integer newRoleID

){}