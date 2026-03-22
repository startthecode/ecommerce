package org.authetication.ecommerce.Mapping;

import org.authetication.ecommerce.dto.response.UserDTLAllResponse;
import org.authetication.ecommerce.dto.response.UserDtlReponse;
import org.authetication.ecommerce.entity.user.UserDtlEntity;
import org.authetication.ecommerce.entity.user.UserEntity;

import java.util.HashMap;
import java.util.Map;

public class UserFillDtlMapper {
    public  static UserDTLAllResponse toResponse(UserEntity user, UserDtlEntity userDtl){
        return  new UserDTLAllResponse(
                user.getuserid(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getRole(),
                userDtl.getAddress(),
                userDtl.getPincode()
        );
    }


}
