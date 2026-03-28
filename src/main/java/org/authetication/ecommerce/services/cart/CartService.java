package org.authetication.ecommerce.services.cart;

import jakarta.transaction.Transactional;
import org.authetication.ecommerce.Mapping.cart.CartMapper;
import org.authetication.ecommerce.dto.request.cart.AddToCartReqDto;
import org.authetication.ecommerce.dto.response.CartResponseDto;
import org.authetication.ecommerce.entity.cart.CartEntity;
import org.authetication.ecommerce.entity.cart.CartItemsEntity;
import org.authetication.ecommerce.entity.product.ProductEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.exception.GenericException;
import org.authetication.ecommerce.repository.cart.CartItemRepository;
import org.authetication.ecommerce.repository.cart.CartRepository;
import org.authetication.ecommerce.services.imp.UserPrincipleImp;
import org.authetication.ecommerce.services.product.ProductService;
import org.mapstruct.Context;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.Objects;

@Service
public class CartService {
    CartRepository cartRepository;
    ProductService productService;
    CartMapper cartMapper;
    CartItemRepository cartItemRepository;
    public CartService(CartRepository cartRepository,
                       ProductService productService,
                       CartMapper cartMapper,
                       CartItemRepository cartItemRepository
                       ) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartMapper = cartMapper;
        this.cartItemRepository = cartItemRepository;
    }



    @Transactional
    public CartResponseDto addToCart(Long productID, AddToCartReqDto req){
        ProductEntity product = productService.getProduct(productID).orElseThrow(()-> new GenericException("Product not found"));
        UserPrincipleImp userPrincipal = (UserPrincipleImp) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        assert userPrincipal != null; // Learning - ❤️
        UserEntity user = userPrincipal.getUser();
        CartEntity cart = this.findElseCreate(user);

        // cart item
        CartItemsEntity newItem = cartMapper.toCartItemEntity(req);
        newItem.setProduct(product);
        newItem.setPriceSnapShop(product.getPrice().getPrice());
        newItem.setCart(cart);

        //cart
        cart.getCartItems().add(newItem);
        if(cart.getTotalAmount() != null){
        cart.setTotalAmount(cart.getTotalAmount() + product.getPrice().getPrice());
        }else{
            cart.setTotalAmount(product.getPrice().getPrice());
        }
         CartEntity updatedCard = cartRepository.save(cart);
        return cartMapper.toResponse(updatedCard);
    }


    private CartEntity findElseCreate(UserEntity user){
        return cartRepository.findByUser(user).orElseGet(CartEntity::new);
    }
}
