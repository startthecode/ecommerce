package org.authetication.ecommerce.services.product;

import org.authetication.ecommerce.entity.product.TagsEntity;
import org.authetication.ecommerce.repository.products.TagsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagsService {
    private  TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public Set<TagsEntity> findOrCreateBatch(String[] tags){
        Set<TagsEntity> tagsBatch = new HashSet<>();
        List<TagsEntity> newTags = new ArrayList<>();

        for(String n : tags){
            tagsRepository.findByName(n).ifPresentOrElse(tagsBatch::add,()->{
             newTags.add(new TagsEntity(n));
            });
        }
        tagsRepository.saveAll(newTags).stream().map(tagsBatch::add);
        return  tagsBatch;
    }
}
