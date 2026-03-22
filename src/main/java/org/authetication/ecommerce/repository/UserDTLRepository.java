package org.authetication.ecommerce.repository;

import org.authetication.ecommerce.entity.user.UserDtlEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDTLRepository extends JpaRepository<UserDtlEntity,Long> {
    Optional<UserDtlEntity> findByUser(UserEntity user);
}
