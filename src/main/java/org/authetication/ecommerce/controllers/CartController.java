package org.authetication.ecommerce.controllers;


import jakarta.validation.Valid;
import org.authetication.ecommerce.dto.request.cart.AddToCartReqDto;
import org.authetication.ecommerce.dto.request.cart.UpdateCartReqDto;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.dto.response.CartResponseDto;
import org.authetication.ecommerce.services.cart.CartService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/{productID}/item")
    public ResponseEntity<ApiBaseResponse<CartResponseDto>> AddProduct(@PathVariable Long productID,
                                                                       @Valid @RequestBody AddToCartReqDto req) {

        CartResponseDto cart = cartService.addToCart(productID, req);
        return ResponseEntity
                .ok(new ApiBaseResponse<>(true,
                        "items has been added",
                        cart));
    }


    @GetMapping("/all/item")
    public ResponseEntity<ApiBaseResponse<CartResponseDto>> getCartItems() {
        CartResponseDto cart = cartService.getCartItems();
        return ResponseEntity
                .ok(new ApiBaseResponse<>(true,
                        "Quantity has been updated",
                        cart));
    }


    @PostMapping("/update/{productID}/item")
    public ResponseEntity<ApiBaseResponse<CartResponseDto>> updateQuantity(@PathVariable Long productID,
                                                                       @Valid @RequestBody UpdateCartReqDto req) {

        CartResponseDto cart = cartService.updateQuantity(productID, req);
        return ResponseEntity
                .ok(new ApiBaseResponse<>(true,
                        "Quantity has been updated",
                        cart));
    }

    @DeleteMapping("/delete/{productID}/item")
    public ResponseEntity<ApiBaseResponse<CartResponseDto>> updateQuantity(@PathVariable Long productID
                                                                           ) {

        CartResponseDto cart = cartService.deleteFromCart(productID);
        return ResponseEntity
                .ok(new ApiBaseResponse<>(true,
                        "Product has been removed from cart",
                        cart));
    }
}
