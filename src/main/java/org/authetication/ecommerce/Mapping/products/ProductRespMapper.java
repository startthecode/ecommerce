package org.authetication.ecommerce.Mapping.products;

import java.util.HashMap;
import java.util.Map;

import org.authetication.ecommerce.dto.response.product.ProductReponse;
import org.authetication.ecommerce.entity.product.ProductEntity;

public class ProductRespMapper {
    public  static ProductReponse toResponse(ProductEntity productEntity) {
        Map<String, String> imagesMap = new HashMap<>();
         if(productEntity.getImages() != null){
         imagesMap.put("imagelg", productEntity.getImages().getImagelg());
        imagesMap.put("imagesm", productEntity.getImages().getImagesm());
        imagesMap.put("thumbnail", productEntity.getImages().getThumbnail());
         }else {
             imagesMap = null;
         }
        return new ProductReponse(
                productEntity.getTitle(),
                productEntity.getDescription(),
                productEntity.getBrand().getName(),
                productEntity.getPrice().getPrice(),
                productEntity.getPrice().getCompare_at_price(),
                productEntity.getStatus().getName().name(),
                imagesMap,
                productEntity.getTags().stream().map(tag -> tag.getName()).toArray(String[]::new),
                productEntity.getCategories().stream().map(category -> category.getName()).toArray(String[]::new),
                productEntity.getStock_quantity(),
                productEntity.getLength(),
                productEntity.getWidth(),
                productEntity.getHeight()
        );
    }


}
