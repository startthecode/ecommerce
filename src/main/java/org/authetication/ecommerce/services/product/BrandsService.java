package org.authetication.ecommerce.services.product;

import org.authetication.ecommerce.entity.product.BrandEntity;
import org.authetication.ecommerce.repository.products.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandsService {
    private final BrandRepository brandRepository;

    public BrandsService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandEntity findOrCreate(String brandName){
        return brandRepository.findByName(brandName).orElseGet(()->brandRepository.save(new BrandEntity(brandName)));
    }




}
