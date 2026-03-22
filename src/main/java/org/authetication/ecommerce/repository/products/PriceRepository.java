package org.authetication.ecommerce.repository.products;
import org.authetication.ecommerce.entity.product.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepository  extends JpaRepository<PriceEntity,Long> {
    Optional<PriceEntity> findByPriceid(Long priceid);
}
