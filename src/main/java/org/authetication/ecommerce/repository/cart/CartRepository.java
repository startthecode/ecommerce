package org.authetication.ecommerce.repository.cart;

import org.authetication.ecommerce.entity.cart.CartEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
    Optional<CartEntity> findByUser(UserEntity user);
}
