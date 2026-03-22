package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "price_table")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long priceid;


    @NotNull
    @Column(nullable = false)
    Float price;

    @NotNull
    @Column(nullable = true)
    Float compare_at_price;

    public PriceEntity(Float compare_at_price, Float price) {
        this.compare_at_price = compare_at_price;
        this.price = price;
    }

    public PriceEntity() {
    }

    public Long getPriceid() {
        return priceid;
    }



    public Float getCompare_at_price() {
        return compare_at_price;
    }

    public void setCompare_at_price(Float compare_at_price) {
        this.compare_at_price = compare_at_price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
