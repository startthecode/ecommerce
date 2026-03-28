package org.authetication.ecommerce.controllers;


import jakarta.validation.Valid;
import org.authetication.ecommerce.dto.request.cart.AddToCartReqDto;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/cart")
public class CartController {

    @PostMapping("/add/{item}/item")
    public Long AddProduct(@PathVariable Long item, @Valid @RequestBody AddToCartReqDto req){
        return item;
    }
}
