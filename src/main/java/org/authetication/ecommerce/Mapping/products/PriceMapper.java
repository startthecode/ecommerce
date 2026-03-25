package org.authetication.ecommerce.Mapping.products;

import org.authetication.ecommerce.dto.request.products.PriceDto;
import org.authetication.ecommerce.entity.product.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
     public PriceEntity toEntity(PriceDto priceDto);
     public PriceDto toDto(PriceEntity priceEntity);
}
