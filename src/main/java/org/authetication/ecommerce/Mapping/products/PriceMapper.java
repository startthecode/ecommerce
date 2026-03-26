package org.authetication.ecommerce.Mapping.products;

import org.authetication.ecommerce.dto.request.products.ImagesDto;
import org.authetication.ecommerce.dto.request.products.PriceDto;
import org.authetication.ecommerce.entity.product.ImagesEntity;
import org.authetication.ecommerce.entity.product.PriceEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PriceMapper {
     public PriceEntity toEntity(PriceDto priceDto);
     public PriceDto toDto(PriceEntity priceEntity);

     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     void toUpdateEntity(PriceDto priceDto, @MappingTarget PriceEntity price);
}
