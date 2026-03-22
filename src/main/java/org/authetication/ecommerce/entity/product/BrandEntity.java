package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import org.authetication.ecommerce.enums.Status;

@Entity
@Table(name = "brand_table")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long brandid;

    @Column(nullable = false,unique = true)
    private Status name;

    public BrandEntity( Status name) {
        this.name = name;
    }

    public BrandEntity( ) {
    }

    public Long getBrandid() {
        return brandid;
    }

    public Status getName() {
        return name;
    }

    public void setName(Status name) {
        this.name = name;
    }
}
