package org.authetication.ecommerce.repository.products;

import org.authetication.ecommerce.entity.product.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagsRepository extends JpaRepository<TagsEntity,Long> {
    Optional<TagsEntity> findByTagid(Long tag_id);
    Optional<TagsEntity> findByName(String name);
}
