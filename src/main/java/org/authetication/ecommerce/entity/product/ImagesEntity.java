package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "images_table")
public class ImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long imageid;

    @NotBlank
    @Column(nullable = true)
    String imagelg;

    @NotBlank
    @Column(nullable = true)
    String imagesm;

    @NotBlank
    @Column(nullable = true)
    String thumbnail;

    public ImagesEntity(){};
    public ImagesEntity(String imagelg, String imagesm, String thumbnail) {
        this.imagelg = imagelg;
        this.imagesm = imagesm;
        this.thumbnail = thumbnail;
    }


    public Long getImageid() {
        return imageid;
    }


    public String getImagelg() {
        return imagelg;
    }

    public void setImagelg(String imagelg) {
        this.imagelg = imagelg;
    }

    public String getImagesm() {
        return imagesm;
    }

    public void setImagesm(String imagesm) {
        this.imagesm = imagesm;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
