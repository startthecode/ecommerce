package org.authetication.ecommerce.Mapping.products;

import org.authetication.ecommerce.dto.request.products.ImagesDto;
import org.authetication.ecommerce.entity.product.ImagesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImagesEntity toEntity(ImagesDto imagesDto);
    ImagesDto toDto(ImagesEntity imagesEntity);
}