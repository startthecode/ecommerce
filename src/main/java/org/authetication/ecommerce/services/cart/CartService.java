package org.authetication.ecommerce.services.cart;

import jakarta.transaction.Transactional;
import org.authetication.ecommerce.Mapping.cart.CartMapper;
import org.authetication.ecommerce.dto.request.cart.AddToCartReqDto;
import org.authetication.ecommerce.dto.request.cart.UpdateCartReqDto;
import org.authetication.ecommerce.dto.response.CartResponseDto;
import org.authetication.ecommerce.entity.cart.CartEntity;
import org.authetication.ecommerce.entity.cart.CartItemsEntity;
import org.authetication.ecommerce.entity.product.ProductEntity;
import org.authetication.ecommerce.entity.user.UserEntity;
import org.authetication.ecommerce.exception.GenericException;
import org.authetication.ecommerce.repository.cart.CartItemRepository;
import org.authetication.ecommerce.repository.cart.CartRepository;
import org.authetication.ecommerce.services.UserService;
import org.authetication.ecommerce.services.imp.UserPrincipleImp;
import org.authetication.ecommerce.services.product.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CartService {
    CartRepository cartRepository;
    ProductService productService;
    CartMapper cartMapper;
    CartItemRepository cartItemRepository;
    UserService userService;
    public CartService(CartRepository cartRepository,
                       ProductService productService,
                       CartMapper cartMapper,
                       CartItemRepository cartItemRepository,
                       UserService userService
                       ) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartMapper = cartMapper;
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
    }



    @Transactional
    public CartResponseDto addToCart(Long productID, AddToCartReqDto req){
        ProductEntity product = productService.getProduct(productID).orElseThrow(()-> new GenericException("Product not found"));
        UserPrincipleImp userPrincipal = (UserPrincipleImp) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        assert userPrincipal != null; // Learning - ❤️
        UserEntity user = userPrincipal.getUser();
        CartEntity cart = this.findElseCreate(user);
        if(!cart.getCartItems().stream().filter((e) ->
                Objects.equals(e.getProduct().getProductid(),
                        productID)).toList().isEmpty()){
         throw  new GenericException("Product already is in the cart");
        }
        // cart item
        CartItemsEntity newItem = cartMapper.toCartItemEntity(req);
        newItem.setProduct(product);
        newItem.setPriceSnapShop(product.getPrice().getPrice());
        newItem.setCart(cart);

        //cart
        cart.getCartItems().add(newItem);
        cart.setUser(user);
        if(cart.getTotalAmount() != null){
        cart.setTotalAmount(cart.getTotalAmount() + (product.getPrice().getPrice() * req.quantity()));
        }else{
            cart.setTotalAmount(product.getPrice().getPrice() * req.quantity());
        }
         CartEntity updatedCard = cartRepository.save(cart);
//        cartItemRepository.save(newItem);
        return cartMapper.toResponse(updatedCard);
    }

    public CartResponseDto getCartItems(){
        UserEntity user = userService.getCurrentUser();
        CartEntity userCart = cartRepository.findByUser(user).orElseThrow(()-> new GenericException("Your cart is empty"));
        return cartMapper.toResponse(userCart);
    }

    @Transactional
    public CartResponseDto updateQuantity(Long productID, UpdateCartReqDto req){
        UserEntity user = userService.getCurrentUser();
        CartEntity userCart = cartRepository.findByUser(user).orElseThrow(()-> new GenericException("Your cart is empty"));
        ProductEntity product =  productService.getProduct(productID).orElseThrow(()-> new GenericException("Product does not exists"));
        CartItemsEntity item = cartItemRepository.findByCartAndProduct(userCart,product).orElseThrow(()-> new GenericException("Product does not found in cart"));
        double lastPrice = item.getPriceSnapShop() * item.getQuantity();
        double newPrice = item.getPriceSnapShop() * req.quantity();
        cartMapper.toUpdateCartItemEntity(req,item);
//        cartItemRepository.save(item);
        userCart.setTotalAmount((userCart.getTotalAmount() - lastPrice) + newPrice);
        return cartMapper.toResponse(userCart);

    }

    @Transactional
    public CartResponseDto deleteFromCart(Long productID){
        UserEntity user = userService.getCurrentUser();
        CartEntity userCart = cartRepository.findByUser(user).orElseThrow(()-> new GenericException("Your cart is empty"));
        ProductEntity product =  productService.getProduct(productID).orElseThrow(()-> new GenericException("Product does not exists"));
        CartItemsEntity item = cartItemRepository.findByCartAndProduct(userCart,product).orElseThrow(()-> new GenericException("Product does not found in cart"));
        userCart.getCartItems().remove(item);

        double total = userCart.getCartItems().stream()
                .filter(i -> !i.getProduct().getProductid().equals(productID))
                .mapToDouble(i -> i.getPriceSnapShop() * i.getQuantity())
                .sum();
        userCart.setTotalAmount(total);
        return cartMapper.toResponse(userCart);
    }

    @Transactional
    public void removeCart(CartEntity cart){
        cartRepository.delete(cart);
    }

    public CartEntity getUserCart(UserEntity user){
        return cartRepository.findByUser(user).orElseThrow(()-> new GenericException("Your cart is empty"));
    }

    private CartEntity findElseCreate(UserEntity user){
        return cartRepository.findByUser(user).orElseGet(CartEntity::new);
    }
}
