package org.authetication.ecommerce.dto.request.products;

import jakarta.validation.constraints.NotBlank;

public record ImagesDto(
    @NotBlank(message = "Image URL can not be blank")
    String imagelg,
    String imagesm,
    String thumbnail


) {
    public ImagesDto{
        if(imagesm == null){
            imagesm = imagelg;
        }
        if(thumbnail == null){
            thumbnail = imagelg;
        }
    }

}
