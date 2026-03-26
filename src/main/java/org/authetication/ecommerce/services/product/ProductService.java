package org.authetication.ecommerce.services.product;

import jakarta.transaction.Transactional;
import org.authetication.ecommerce.Mapping.products.PriceMapper;
import org.authetication.ecommerce.Mapping.products.ProductMapper;
import org.authetication.ecommerce.dto.request.products.ProductRequestDto;
import org.authetication.ecommerce.dto.request.products.ProductUpdateReqDto;
import org.authetication.ecommerce.dto.response.product.ProductResponse;
import org.authetication.ecommerce.entity.product.*;
import org.authetication.ecommerce.enums.Status;
import org.authetication.ecommerce.exception.GenericException;
import org.authetication.ecommerce.repository.products.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final PriceRepository priceRepository;
    private final ImagesRepository imagesRepository;
    ProductRepository productRepository;
    StatusRepository statusRepository;
    BrandsService brandsService;
    TagsService tagsService;
    CategoryService categoryService;
    PriceMapper priceMapper;
    private final ProductMapper mapper;

    public ProductService(
            ProductRepository productRepository,
            StatusRepository statusRepository,
            ProductMapper mapper,
            BrandsService brandsService,
            TagsService tagsService,
            CategoryService categoryService,
            PriceMapper priceMapper,
            PriceRepository priceRepository, ImagesRepository imagesRepository) {
        this.productRepository = productRepository;
        this.statusRepository = statusRepository;
        this.brandsService = brandsService;
        this.tagsService = tagsService;
        this.categoryService = categoryService;
        this.mapper = mapper;
        this.priceMapper  = priceMapper;
        this.priceRepository = priceRepository;
        this.imagesRepository = imagesRepository;
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

    @Transactional
    public ProductResponse updateProduct(Long productID, ProductUpdateReqDto req){
        ProductEntity product = productRepository.findById(productID).orElseThrow(()->new GenericException("Invalid product id"));
        mapper.toEntityUpdate(req,product);
        System.out.printf("%s -----------",product.getTitle());
        if(req.brand() != null) product.setBrand(brandsService.findOrCreate(req.brand()));
        if(req.price() != null) {
            PriceEntity prie = priceRepository.findById(product.getPrice().getPriceid()).orElseThrow(()->new GenericException("price get fails"));
            priceMapper.toUpdateEntity(req.price(),prie);
            priceRepository.save(prie);
        };
        if(req.images() != null){
            ImagesEntity images = imagesRepository.findById(product.getImages().getImageid()).orElseThrow(()->new GenericException("price get fails"));
            if(req.images().imagesm() != null) images.setImagesm(req.images().imagesm());
            if(req.images().thumbnail() != null) images.setThumbnail(req.images().thumbnail());
            if(req.images().imagelg() != null) images.setImagelg(req.images().imagelg());
            imagesRepository.save(images);
        }
        if(req.tags() != null) product.setTags(tagsService.findOrCreateBatch(req.tags()));
        if(req.categories() != null) product.setCategories(categoryService.findOrCreateBatch(req.categories()));
        if(req.status() != null && !req.status().name().equals(product.getStatus().getName().name())) product.setStatus(statusRepository.findAllByName(Status.valueOf(String.valueOf(req.status()))).orElseThrow(()->new GenericException("Invalid Status")));
        ProductEntity response = productRepository.save(product);
        return mapper.tResponse(productRepository.save(response));
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
