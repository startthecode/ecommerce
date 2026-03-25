package org.authetication.ecommerce.repository.products;

import org.authetication.ecommerce.entity.product.ProductEntity;
import org.authetication.ecommerce.entity.product.StatusEntity;
import org.authetication.ecommerce.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findByProductid(Long id);
    Optional<ProductEntity> findByTitleContains(String query);
    Optional<ProductEntity> findByTitle(String query);
    List<ProductEntity> findAllByStatus(StatusEntity status);
}
