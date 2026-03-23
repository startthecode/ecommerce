package org.authetication.ecommerce.controllers;

import jakarta.validation.Valid;
import org.authetication.ecommerce.Mapping.products.ProductRespMapper;
import org.authetication.ecommerce.dto.request.AddProductRequest;
import org.authetication.ecommerce.dto.response.ApiBaseResponse;
import org.authetication.ecommerce.dto.response.product.ProductReponse;
import org.authetication.ecommerce.entity.product.ProductEntity;
import org.authetication.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiBaseResponse<ProductReponse>> addProduct(@Valid @RequestBody AddProductRequest request) {
        ProductEntity newProduct = this.productService.addProduct(request);
        return ResponseEntity.ok(new ApiBaseResponse<>(true,"Product has been created", ProductRespMapper.toResponse(newProduct)));
    }
}
