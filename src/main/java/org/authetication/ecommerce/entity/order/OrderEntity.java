package org.authetication.ecommerce.entity.order;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.authetication.ecommerce.entity.payment.PaymentEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.enums.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_table")
public class OrderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq",sequenceName = "order_id_seq",allocationSize = 10)
    Long orderId;

    @ManyToOne
    @JoinColumn(name = "userid")
    UserEntity user;

    @Column(nullable = false)
    Double totalAmount;
 
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus; 
    
    @Column(nullable = false)
    String address;

    @OneToOne(mappedBy = "order",cascade = CascadeType.PERSIST)
    PaymentEntity payment;

    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    Set<OrderItemsEntity> orderItems = new HashSet<>();

    @CreationTimestamp
    LocalDate createdAt;

    public OrderEntity(String address,
                       Set<OrderItemsEntity> orderItems,
                       OrderStatus orderStatus,
                       PaymentEntity payment,
                       Double totalAmount,
                       UserEntity user) {
        this.address = address;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.payment = payment;
        this.totalAmount = totalAmount;
        this.user = user;
    }

    public OrderEntity() {
        }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }



    public Set<OrderItemsEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemsEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
