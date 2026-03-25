package org.authetication.ecommerce.services.product;

import jakarta.transaction.Transactional;
import org.authetication.ecommerce.Mapping.products.ProductMapper;
import org.authetication.ecommerce.dto.request.products.ProductRequestDto;
import org.authetication.ecommerce.dto.response.product.ProductResponse;
import org.authetication.ecommerce.entity.product.*;
import org.authetication.ecommerce.enums.Status;
import org.authetication.ecommerce.exception.GenericException;
import org.authetication.ecommerce.repository.products.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;
    StatusRepository statusRepository;
    BrandsService brandsService;
    TagsService tagsService;
    CategoryService categoryService;
    private final ProductMapper mapper;

    public ProductService(
            ProductRepository productRepository,
            StatusRepository statusRepository,
            ProductMapper mapper,
            BrandsService brandsService,
            TagsService tagsService,
            CategoryService categoryService
    ) {
        this.productRepository = productRepository;
        this.statusRepository = statusRepository;
        this.brandsService = brandsService;
        this.tagsService = tagsService;
        this.categoryService = categoryService;
        this.mapper = mapper;

    }

    @Transactional
    public ProductResponse createProduct(ProductRequestDto productPayload) {
        if (productRepository.findByTitle(productPayload.title()).isPresent())
            throw new GenericException("product already exist");

        ProductEntity newProduct = mapper.toEntity(productPayload);
        if (productPayload.brand() != null) newProduct.setBrand(brandsService.findOrCreate(productPayload.brand()));
        if (productPayload.tags() != null && productPayload.tags().length > 0)
            newProduct.setTags(tagsService.findOrCreateBatch(productPayload.tags()));
        if (productPayload.categories() != null && productPayload.categories().length > 0)
            newProduct.setCategories(categoryService.findOrCreateBatch(productPayload.categories()));
        newProduct.setStatus(
                statusRepository.findAllByName(productPayload.status())
                        .orElseGet(() -> statusRepository
                                .findAllByName(Status.ACTIVE)
                                .orElseThrow(() -> new GenericException("Unable to update status")))
        );
        return mapper.tResponse(productRepository.save(newProduct));
    }


    public List<ProductResponse> getAllProducts(){
        return productRepository
                .findAllByStatus(statusRepository.
                        findAllByName(Status.ACTIVE)
                        .orElseThrow())
                .stream()
                .map(mapper::tResponse)
                .toList();
    }

//    @Transactional
//    public ProductResponse addProduct(AddProductRequest addProductRequest) {
//
//        if (this.getProductById(addProductRequest.title()) != null) {
//            throw ProductException.productAlreadyExists("title", addProductRequest.title());
//        }
//
//        ProductEntity newProduct = new ProductEntity();
//        if (addProductRequest.brand() != null) {
//            brandRepository.findByName(addProductRequest.brand())
//                    .ifPresentOrElse(
//                            newProduct::setBrand,
//                            () -> {
//                                BrandEntity brandEntity = new BrandEntity();
//                                brandEntity.setName(addProductRequest.brand());
//                                newProduct.setBrand(brandRepository.save(brandEntity));
//                            });
//        } else {
//            newProduct.setBrand(null);
//        }
//        if (addProductRequest.tags() != null && addProductRequest.tags().length != 0) {
//            HashSet<TagsEntity> tagSet = new HashSet<>();
//            HashSet<TagsEntity> newTags = new HashSet<>();
//            for (String names : addProductRequest.tags()) {
//                tagsRepository.findByName(names).ifPresentOrElse(tagSet::add, () -> {
//                    newTags.add(new TagsEntity(names));
//                });
//            }
//            tagSet.addAll(tagsRepository.saveAll(newTags));
//            newProduct.setTags(tagSet);
//        } else {
//            newProduct.setTags(null);
//        }
//        if (addProductRequest.categories() != null && addProductRequest.categories().length != 0) {
//            HashSet<CategoryEntity> categorySet = new HashSet<>();
//            HashSet<CategoryEntity> newCategories = new HashSet<>();
//            for (String names : addProductRequest.categories()) {
//                categoryRepository.findAllByName(names).ifPresentOrElse(categorySet::add, () -> {
//                    newCategories.add(new CategoryEntity(names));
//                });
//            }
//            categorySet.addAll(categoryRepository.saveAll(newCategories));
//            newProduct.setCategories(categorySet);
//        } else {
//            newProduct.setCategories(null);
//        }
//        if (addProductRequest.images() != null && !addProductRequest.images().isBlank()) {
//            ImagesEntity imagesEntity = new ImagesEntity(
//                    addProductRequest.images(),
//                    addProductRequest.images(),
//                    addProductRequest.images());
//            newProduct.setImages(imagesRepository.save(imagesEntity));
//        } else {
//            newProduct.setImages(null);
//        }
//        PriceEntity price = priceRepository
//                .save(new PriceEntity(addProductRequest.comparePrice(), addProductRequest.price()));
//        StatusEntity status = statusRepository
//                .findAllByName(Status.valueOf(addProductRequest.status().name().toUpperCase()))
//                .orElseThrow(() -> new GenericException("invalid  product status"));
//        newProduct.setTitle(addProductRequest.title());
//        newProduct.setDescription(addProductRequest.description());
//        newProduct.setPrice(price);
//        newProduct.setStatus(status);
//        newProduct.setHeight(addProductRequest.height());
//        newProduct.setLength(addProductRequest.length());
//        newProduct.setWidth(addProductRequest.width());
//        newProduct.setStock_quantity(addProductRequest.stock_quantity());
//        newProduct.setShort_description(addProductRequest.short_description());
//        return productRepository.save(newProduct);


//        ProductEntity product = mapper.toEntity(addProductRequest);
//        return mapper.toResponse(product);
//    }

//    public List<ProductEntity> getAllproducts(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size); // page 0, size 10
//        StatusEntity activeStatus = statusRepository.findAllByName(Status.ACTIVE)
//                .orElseThrow(() -> new GenericException("Active status not found"));
//                Page<ProductEntity> products = productRepository.findByStatus(activeStatus, pageable);
//        return products.getContent();
//    }
//
//    private ProductEntity getProductById(String str) {
//        return productRepository.findByTitle(str).orElse(null);
//    }

}
