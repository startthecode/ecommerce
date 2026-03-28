package org.authetication.ecommerce.repository.cart;

import org.authetication.ecommerce.entity.cart.CartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemsEntity,Long> {
}
