package org.authetication.ecommerce.Mapping.cart;

import org.authetication.ecommerce.dto.request.cart.AddToCartReqDto;
import org.authetication.ecommerce.dto.request.cart.UpdateCartReqDto;
import org.authetication.ecommerce.dto.response.CartResponseDto;
import org.authetication.ecommerce.entity.cart.CartEntity;
import org.authetication.ecommerce.entity.cart.CartItemsEntity;
import org.mapstruct.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "cartItems", expression = "java(mapItems(cart))")
    public CartResponseDto toResponse(CartEntity cart);
    default Set<Object> mapItems(CartEntity cart) {
        return cart
                .getCartItems()
                .stream()
                .map((e) -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("productid", e.getProduct().getProductid());
                    map.put("quantity", e.getQuantity());
                    map.put("price", e.getPriceSnapShop());
                    return map;
                }).
                collect(Collectors.toSet());
    }

    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "priceSnapShop", ignore = true)
    public CartItemsEntity toCartItemEntity(AddToCartReqDto req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "priceSnapShop", ignore = true)
    public void toUpdateCartItemEntity(UpdateCartReqDto req, @MappingTarget CartItemsEntity cartItems);




}
