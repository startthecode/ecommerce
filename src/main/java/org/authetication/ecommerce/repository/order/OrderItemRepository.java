package org.authetication.ecommerce.repository.order;

import java.util.Set;

import org.authetication.ecommerce.entity.order.OrderEntity;
import org.authetication.ecommerce.entity.order.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemsEntity,Long>{
Set<OrderItemsEntity> findByOrder(OrderEntity order);
}
