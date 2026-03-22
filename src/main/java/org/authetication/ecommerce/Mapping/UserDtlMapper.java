package org.authetication.ecommerce.Mapping;

import org.authetication.ecommerce.dto.response.UserDtlReponse;
import org.authetication.ecommerce.entity.user.UserDtlEntity;
import org.authetication.ecommerce.entity.user.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserDtlMapper {
    public  static UserDtlReponse toResponse(UserEntity user, UserDtlEntity userDtl){
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("address",userDtl.getAddress());
        return  new UserDtlReponse(userDetails,user.getuserid(), user.getUsername());
    }
}
