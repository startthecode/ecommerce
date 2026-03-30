package org.authetication.ecommerce.repository.order;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.authetication.ecommerce.entity.order.OrderEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long>{
    Set<OrderEntity> findByUserAndOrderStatusIn(UserEntity user, List<OrderStatus> status);
    Optional<OrderEntity> findByOrderId(Long id);
}
