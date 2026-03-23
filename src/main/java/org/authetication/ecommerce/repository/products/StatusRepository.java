package org.authetication.ecommerce.repository.products;

import org.authetication.ecommerce.entity.product.ImagesEntity;
import org.authetication.ecommerce.entity.product.StatusEntity;
import org.authetication.ecommerce.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusEntity,Long> {
    Optional<StatusEntity> findAllByName(Status name);
    Optional<StatusEntity> findAllByStatusid(Long imageid);
}
