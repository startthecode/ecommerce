package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;

@Entity
@Table(name = "brand_table")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long brandid;

    @Column(nullable = false,unique = true)
    private String name;

    public BrandEntity( String name) {
        this.name = name;
    }

    public BrandEntity( ) {
    }

    public Long getBrandid() {
        return brandid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
