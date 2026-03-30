package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.authetication.ecommerce.entity.cart.CartItemsEntity;
import org.authetication.ecommerce.entity.order.OrderItemsEntity;
import org.authetication.ecommerce.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    private double length;

    @Column(nullable = true)
    private double width;

    @Column(nullable = true)
    private double height;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image")
    private ImagesEntity images;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price")
    private PriceEntity price;

    @ManyToMany
    @JoinTable(name = "tags_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<TagsEntity> tags;

    @ManyToMany
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
     Set<CartItemsEntity> cartItems =  new HashSet<>();


    @OneToMany(mappedBy = "product")
    Set<OrderItemsEntity> order;


    @PreUpdate
    @PrePersist
    public void setPublished_at() {
        if (status != null && status.getName() == Status.ACTIVE && published_at == null) {
            published_at = LocalDateTime.now();
        }
    }

    public ProductEntity(BrandEntity brand, Set<CategoryEntity> categories, LocalDateTime created_at,
            String description, double height, ImagesEntity images, double length, PriceEntity price,
            LocalDateTime published_at, String short_description, StatusEntity status, int stock_quantity,
            Set<TagsEntity> tags, String title, LocalDateTime updated_at, double width) {
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

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public ImagesEntity getImages() {
        return images;
    }

    public void setImages(ImagesEntity images) {
        this.images = images;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
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
        return this.stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public Set<TagsEntity> getTags() {
        return tags;
    }

    public void setTags(Set<TagsEntity> tags) {
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
