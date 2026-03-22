package org.authetication.ecommerce.dto.response;

import java.util.Map;

public record UserDtlReponse(
        Map userdetail,
        Long useriD,
        String username
) {

}
