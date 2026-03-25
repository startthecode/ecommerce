package org.authetication.ecommerce.Mapping.products;

import org.authetication.ecommerce.dto.request.AddProductRequest;
import org.authetication.ecommerce.dto.request.products.ProductRequestDto;
import org.authetication.ecommerce.dto.response.product.ProductResponse;
import org.authetication.ecommerce.entity.product.CategoryEntity;
import org.authetication.ecommerce.entity.product.ProductEntity;
import org.authetication.ecommerce.entity.product.TagsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.HashSet;

@Mapper(componentModel = "spring",uses = {PriceMapper.class,ImageMapper.class})
public interface ProductMapper {
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "status", ignore = true)
public ProductEntity toEntity(ProductRequestDto request);


    @Mapping(target = "brand", source = "brand.name")
    @Mapping(target = "status",source = "status.name")
    @Mapping(target = "categories", expression = "java(mapCategories(productEntity))" )
    @Mapping(target = "tags", expression = "java(mapTags(productEntity))")
public ProductResponse tResponse(ProductEntity productEntity);

    default String[] mapTags(ProductEntity productEntity){
        if(productEntity.getTags() == null)
            return null;
        return productEntity.getTags() .stream()
                .map(TagsEntity::getName)
                .toArray(String[]::new);
    }

    default String[] mapCategories(ProductEntity productEntity){
        if(productEntity.getCategories() == null)
            return null;
        return productEntity.getCategories() .stream()
                .map(CategoryEntity::getName)
                .toArray(String[]::new);
    }
}
