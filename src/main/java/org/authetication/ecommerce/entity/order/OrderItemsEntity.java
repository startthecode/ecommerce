package org.authetication.ecommerce.entity.order;

import org.authetication.ecommerce.entity.product.ProductEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items_table")
public class OrderItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq",sequenceName = "order_item_seq" , allocationSize = 10 )
    Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    OrderEntity order;
    
    @Column(nullable = false)
    Double priceSnapShot;

    @ManyToOne
    @JoinColumn(name = "productid")
    ProductEntity product;

    @Column(nullable = false)
    int quantity;

    public OrderItemsEntity(OrderEntity order,  Double priceSnapShot, ProductEntity product, int quantity) {
        this.order = order;
        this.priceSnapShot = priceSnapShot;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItemsEntity() {}

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }



    public Double getPriceSnapShot() {
        return priceSnapShot;
    }

    public void setPriceSnapShot(Double priceSnapShot) {
        this.priceSnapShot = priceSnapShot;
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
