package org.authetication.ecommerce.Mapping.cart;

import org.authetication.ecommerce.dto.request.cart.AddToCartReqDto;
import org.authetication.ecommerce.dto.response.CartResponseDto;
import org.authetication.ecommerce.entity.cart.CartEntity;
import org.authetication.ecommerce.entity.cart.CartItemsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "cartItems", expression = "java(mapItems(cart))")
    public CartResponseDto toResponse(CartEntity cart);
    private Set<Long> mapItems(CartEntity cart) {
        return cart
                .getCartItems()
                .stream()
                .map((e) -> e.getProduct()
                        .getProductid()).
                collect(Collectors.toSet());
    }

    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "priceSnapShop", ignore = true)
    public CartItemsEntity toCartItemEntity(AddToCartReqDto req);


}
