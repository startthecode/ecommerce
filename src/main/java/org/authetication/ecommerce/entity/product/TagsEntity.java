package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "tag_table")
public class TagsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long tagid;
    @NotBlank
    @Column(nullable = false, unique = true)
    String name;

    public TagsEntity() {
    }

    public TagsEntity(String name) {
        this.name = name;
        this.tagid = tagid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTagid() {
        return tagid;
    }

}
