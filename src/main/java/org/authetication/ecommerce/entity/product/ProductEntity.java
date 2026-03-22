package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.authetication.ecommerce.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Table(name = "product_table")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productid;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String title;

    @NotBlank
    @Length(max = 250)
    @Column()
    private String description;

    @Length(max = 60)
    @Column()
    private String short_description;

    @NotNull
    @Column(nullable = false)
    private int stock_quantity;

    @Column(nullable = true)
    private float length;

    @Column(nullable = true)
    private float width;

    @Column(nullable = true)
    private float height;

    @Column(nullable = true)
    private LocalDateTime published_at;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "status")
    private StatusEntity status;

    @ManyToOne
    @JoinColumn(name = "brand")
    private BrandEntity brand;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image")
    private ImagesEntity images;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "price")
    private PriceEntity price;

    @ManyToMany
    @JoinTable(name = "tags_categories", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private HashSet<TagsEntity> tags;

    @ManyToMany
    @JoinTable(name = "products_categories", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private HashSet<CategoryEntity> categories;

    @PreUpdate
    @PrePersist
    public void setPublished_at() {
        if (status.getName() == Status.ACTIVE && status == null) {
            published_at = LocalDateTime.now();
        }
    }

    public ProductEntity(BrandEntity brand, HashSet<CategoryEntity> categories, LocalDateTime created_at,
            String description, float height, ImagesEntity images, float length, PriceEntity price,
            LocalDateTime published_at, String short_description, StatusEntity status, int stock_quantity,
            HashSet<TagsEntity> tags, String title, LocalDateTime updated_at, float width) {
        this.brand = brand;
        this.categories = categories;
        this.created_at = created_at;
        this.description = description;
        this.height = height;
        this.images = images;
        this.length = length;
        this.price = price;
        this.published_at = published_at;
        this.short_description = short_description;
        this.status = status;
        this.stock_quantity = stock_quantity;
        this.tags = tags;
        this.title = title;
        this.updated_at = updated_at;
        this.width = width;
    }

    public ProductEntity() {
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public HashSet<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(HashSet<CategoryEntity> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public ImagesEntity getImages() {
        return images;
    }

    public void setImages(ImagesEntity images) {
        this.images = images;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public Long getProductid() {
        return productid;
    }

    public LocalDateTime getPublished_at() {
        return published_at;
    }

    public void setPublished_at(LocalDateTime published_at) {
        this.published_at = published_at;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public HashSet<TagsEntity> getTags() {
        return tags;
    }

    public void setTags(HashSet<TagsEntity> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
