package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "category_table")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long categoryid;
    @NotBlank
    @Column(nullable = false, unique = true)
    String name;

    public CategoryEntity(String name) {
        this.name = name;
    }

    public CategoryEntity() {

    }

    public Long getCategoryid() {
        return categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
