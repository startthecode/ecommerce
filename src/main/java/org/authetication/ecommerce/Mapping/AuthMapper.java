package org.authetication.ecommerce.Mapping;

import org.authetication.ecommerce.dto.request.auth.UserSignUpDto;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "password",ignore = true)
    UserEntity toEntitySignup(UserSignUpDto user);
}
