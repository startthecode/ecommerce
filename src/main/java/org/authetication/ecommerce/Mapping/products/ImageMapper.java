package org.authetication.ecommerce.Mapping.products;

import org.authetication.ecommerce.dto.request.products.ImagesDto;
import org.authetication.ecommerce.entity.product.ImagesEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImagesEntity toEntity(ImagesDto imagesDto);
    ImagesDto toDto(ImagesEntity imagesEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateEntity(ImagesDto imagesDto, @MappingTarget ImagesEntity images);
}