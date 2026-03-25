package org.authetication.ecommerce.repository.products;

import org.authetication.ecommerce.entity.product.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository  extends JpaRepository<BrandEntity,Long> {
    Optional<BrandEntity> findByBrandid(Long id);
    Optional<BrandEntity> findByName(String name);

}