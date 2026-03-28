package org.authetication.ecommerce.entity.cart;

import jakarta.persistence.*;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart_table")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long cartID;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userid")
    UserEntity user;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "cart")
    Set<CartItemsEntity> cartItems = new HashSet<>();

    @Column(nullable = false)
    Double totalAmount;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


    public CartEntity(Long cartID,
                      Set<CartItemsEntity> cartItems,
                      LocalDateTime createdAt,
                      Double totalAmount,
                      LocalDateTime updatedAt,
                      UserEntity user) {
        this.cartID = cartID;
        this.cartItems = cartItems;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public CartEntity() {

    }

    public Long getCartID() {
        return cartID;
    }


    public Set<CartItemsEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItemsEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
