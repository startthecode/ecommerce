package org.authetication.ecommerce.entity.cart;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.authetication.ecommerce.entity.product.ProductEntity;
import org.authetication.ecommerce.entity.user.UserEntity;

@Entity
@Table(name = "cart_item_table")
public class CartItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long cartItemID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "productid")
    ProductEntity product;

    @NotNull
    @Column(nullable = false)
    Double priceSnapShop = 0D;

    @NotNull
    @Column(nullable = false)
    int quantity;

    public CartItemsEntity(ProductEntity product, Double priceSnapShop, Long cartItemID, CartEntity cart) {
        this.product = product;
        this.priceSnapShop = priceSnapShop;
        this.cartItemID = cartItemID;
        this.cart = cart;
    }
    public CartItemsEntity() {
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public Long getCartItemID() {
        return cartItemID;
    }

    public Double getPriceSnapShop() {
        return priceSnapShop;
    }

    public void setPriceSnapShop(Double priceSnapShop) {
        this.priceSnapShop = priceSnapShop;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
