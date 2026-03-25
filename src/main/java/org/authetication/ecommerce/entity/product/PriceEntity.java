package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "price_table")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long priceid;


    @NotNull
    @Column(nullable = false)
    double price;

    @NotNull
    @Column(nullable = true)
    double compare_at_price;

    public PriceEntity(double compare_at_price, double price) {
        this.compare_at_price = compare_at_price;
        this.price = price;
    }

    public PriceEntity( double price) {
        this.price = price;
    }

    public PriceEntity() {
    }

    public Long getPriceid() {
        return priceid;
    }



    public double getCompare_at_price() {
        return compare_at_price;
    }

    public void setCompare_at_price(double compare_at_price) {
        this.compare_at_price = compare_at_price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
