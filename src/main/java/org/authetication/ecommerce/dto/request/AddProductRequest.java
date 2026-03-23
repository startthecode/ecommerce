package org.authetication.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.authetication.ecommerce.enums.Status;



public record AddProductRequest(
         @NotBlank(message = "Title can not be blank")
         String title,

         @NotBlank(message = "description can not be blank")
         String description,

         String short_description,

         @NotNull(message = "stock_quantity can not be blank")
         int stock_quantity,

         @NotNull(message = "stock_quantity can not be blank")
         float length,

         @NotNull(message = "stock_quantity can not be blank")
         float width,

         @NotNull(message = "stock_quantity can not be blank")
         float height,


         Status status,
         String brand,
         String images,
         Long price,
         Long comparePrice,
         String[] tags,
         String[] categories
) {
    public AddProductRequest{
        if(status != null){
            status = Status.ACTIVE;
        }
        if(status == null){
          status = Status.ACTIVE;
        }
        if(short_description == null){
            short_description = description();
        }
    }
}
