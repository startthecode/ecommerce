package org.authetication.ecommerce.repository.cart;

import org.authetication.ecommerce.entity.cart.CartEntity;
import org.authetication.ecommerce.entity.cart.CartItemsEntity;
import org.authetication.ecommerce.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CartItemRepository extends JpaRepository<CartItemsEntity,Long> {
    Optional<CartItemsEntity> findByCartAndProduct(CartEntity cart, ProductEntity product);
}
