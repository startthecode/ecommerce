package org.authetication.ecommerce.controllers;

import jakarta.validation.Valid;
import org.authetication.ecommerce.dto.request.products.ProductRequestDto;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.dto.response.product.ProductResponse;
import org.authetication.ecommerce.services.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
     public ResponseEntity<ApiBaseResponse<ProductResponse>> addProduct(@Valid @RequestBody ProductRequestDto productPayload) {
        return ResponseEntity.ok(new ApiBaseResponse<>(true,"Product has been created", productService.createProduct(productPayload)));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiBaseResponse<List<ProductResponse>>> addProduct() {
        return ResponseEntity.ok(new ApiBaseResponse<>(true,"Product has been created", productService.getAllProducts()));
    }
    // @PostMapping("/add")
    // public ResponseEntity<ApiBaseResponse<ProductResponse>> addProduct(@Valid @RequestBody AddProductRequest request) {
    //     ProductEntity newProduct = this.productService.addProduct(request);
    //     return ResponseEntity.ok(new ApiBaseResponse<>(true,"Product has been created", ProductRespMapper.toResponse(newProduct)));
    // }
}
