package org.authetication.ecommerce.services;

import org.authetication.ecommerce.repository.products.*;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceOld {
    BrandRepository brandRepository;
    CategoryRepository categoryRepository;
    ImagesRepository imagesRepository;
    ProductRepository productRepository;
    PriceRepository priceRepository;
    TagsRepository tagsRepository;
    StatusRepository statusRepository;

    public ProductServiceOld(BrandRepository brandRepository, CategoryRepository categoryRepository,
                             ImagesRepository imagesRepository, ProductRepository productRepository,
                             PriceRepository priceRepository, TagsRepository tagsRepository,
                             StatusRepository statusRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.imagesRepository = imagesRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.tagsRepository = tagsRepository;
        this.statusRepository = statusRepository;

    }
// @Transactional
    // public ProductEntity addProduct(AddProductRequest addProductRequest) {
    //     ProductEntity newProduct = new ProductEntity();
    //     if (addProductRequest.brand() != null) {
    //         brandRepository.findByName(addProductRequest.brand())
    //                 .ifPresentOrElse(
    //                         newProduct::setBrand,
    //                         () -> {
    //                             BrandEntity brandEntity = new BrandEntity();
    //                             brandEntity.setName(addProductRequest.brand());
    //                             newProduct.setBrand(brandRepository.save(brandEntity));
    //                         }
    //                 );
    //     } else {
    //         newProduct.setBrand(null);
    //     }
    //     if (addProductRequest.tags() != null && addProductRequest.tags().length != 0) {
    //         HashSet<TagsEntity> tagSet = new HashSet<>();
    //         HashSet<TagsEntity> newTags = new HashSet<>();
    //         for (String names : addProductRequest.tags()) {
    //             tagsRepository.findByName(names).ifPresentOrElse(tagSet::add, () -> {
    //                 newTags.add(new TagsEntity(names));
    //             });
    //         }
    //         tagSet.addAll(tagsRepository.saveAll(newTags));
    //         newProduct.setTags(tagSet);
    //     } else {
    //         newProduct.setTags(null);
    //     }
    //     if (addProductRequest.categories() != null && addProductRequest.categories().length != 0) {
    //         HashSet<CategoryEntity> categorySet = new HashSet<>();
    //         HashSet<CategoryEntity> newCategories = new HashSet<>();
    //         for (String names : addProductRequest.categories()) {
    //             categoryRepository.findAllByName(names).ifPresentOrElse(categorySet::add, () -> {
    //                 newCategories.add(new CategoryEntity(names));
    //             });
    //         }
    //         categorySet.addAll(categoryRepository.saveAll(newCategories));
    //         newProduct.setCategories(categorySet);
    //     } else {
    //         newProduct.setCategories(null);
    //     }
    //     if (addProductRequest.images() != null && !addProductRequest.images().isBlank()) {
    //         ImagesEntity imagesEntity = new ImagesEntity(
    //                 addProductRequest.images(),
    //                 addProductRequest.images(),
    //                 addProductRequest.images()
    //         );
    //         newProduct.setImages(imagesRepository.save(imagesEntity));
    //     } else {
    //         newProduct.setImages(null);
    //     }
    //     PriceEntity price = priceRepository.save(new PriceEntity(addProductRequest.comparePrice(), addProductRequest.price()));
    //     StatusEntity status = statusRepository.findAllByName(Status.valueOf(addProductRequest.status().name().toUpperCase())).orElseThrow(()->new GenericException("invalid  product status"));
    //     newProduct.setTitle(addProductRequest.title());
    //     newProduct.setDescription(addProductRequest.description());
    //     newProduct.setPrice(price);
    //     newProduct.setStatus(status);
    //     newProduct.setHeight(addProductRequest.height());
    //     newProduct.setLength(addProductRequest.length());
    //     newProduct.setWidth(addProductRequest.width());
    //     newProduct.setStock_quantity(addProductRequest.stock_quantity());
    //     newProduct.setShort_description(addProductRequest.short_description());
    //     return productRepository.save(newProduct);
    // }

}
