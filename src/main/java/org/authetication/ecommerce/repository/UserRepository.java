package org.authetication.ecommerce.repository;

import java.util.Optional;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findAllByUserid(Long id);


}
