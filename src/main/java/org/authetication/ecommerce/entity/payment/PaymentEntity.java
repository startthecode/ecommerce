package org.authetication.ecommerce.entity.payment;

import java.time.LocalDateTime;

import org.authetication.ecommerce.entity.order.OrderEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.enums.PaymentMethod;
import org.authetication.ecommerce.enums.PaymentStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_table")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq",sequenceName = "payment_id_seq", allocationSize = 10)
    Long paymentID;

    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "userid")
    UserEntity user;
    

    @Enumerated(EnumType.STRING)
    PaymentMethod method;

    @Column(nullable = false)
    Double amount;

    @Column(nullable = false)
    String transactionID;

    @OneToOne
    @JoinColumn(name = "orderId")
    OrderEntity order;
    
    @CreationTimestamp
    LocalDateTime createdAt;

    public PaymentEntity(Double amount, LocalDateTime createdAt, PaymentMethod method, OrderEntity order, PaymentStatus status, String transactionID, UserEntity user) {
        this.amount = amount;
        this.createdAt = createdAt;
        this.method = method;
        this.order = order;
        this.status = status;
        this.transactionID = transactionID;
        this.user = user;
    }

    public PaymentEntity() {
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Long getPaymentID() {
        return paymentID;
    }



    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
