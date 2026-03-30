package org.authetication.ecommerce.repository.payment;

import java.util.Set;

import org.authetication.ecommerce.entity.payment.PaymentEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long>{
    Set<PaymentEntity> findByUser(UserEntity user);
    PaymentEntity findByOrderAndStatus(UserEntity user,PaymentStatus status);
}
