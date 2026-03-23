package org.authetication.ecommerce.repository.products;

import java.util.Optional;

import org.authetication.ecommerce.entity.product.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository  extends JpaRepository<ImagesEntity,Long> {
    Optional<ImagesEntity> findByImageid(Long imageid);
}
