package org.authetication.ecommerce.services.product;

import org.authetication.ecommerce.entity.product.CategoryEntity;
import org.authetication.ecommerce.repository.products.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Set<CategoryEntity> findOrCreateBatch(String[] tags){
        Set<CategoryEntity> categoryBatch = new HashSet<>();
        List<CategoryEntity> newTags = new ArrayList<>();

        for(String n : tags){
            categoryRepository.findByName(n).ifPresentOrElse(categoryBatch::add,()->{
                newTags.add(new CategoryEntity(n));
            });
        }
        categoryRepository.saveAll(newTags).stream().map(categoryBatch::add);
        return  categoryBatch;
    }
}
