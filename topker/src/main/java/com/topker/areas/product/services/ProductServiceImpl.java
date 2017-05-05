package com.topker.areas.product.services;

import com.topker.areas.brand.entities.Brand;
import com.topker.areas.brand.models.bindingModels.BrandCreationModel;
import com.topker.areas.brand.repositories.BrandRepository;
import com.topker.areas.brand.services.BrandService;
import com.topker.areas.category.entities.Category;
import com.topker.areas.category.models.bindingModels.CategoryCreationModel;
import com.topker.areas.category.repositories.CategoryRepository;
import com.topker.areas.category.services.CategoryService;
import com.topker.areas.product.entities.BaseProduct;
import com.topker.areas.product.entities.MultiProduct;
import com.topker.areas.product.entities.SingleProduct;
import com.topker.areas.product.models.bindingModels.MultiProductCreationModel;
import com.topker.areas.product.models.bindingModels.SingleProductCreationModel;
import com.topker.areas.product.models.bindingModels.SubProductCreationModel;
import com.topker.areas.product.models.viewModels.ProductUpdateModel;
import com.topker.areas.product.models.viewModels.ProductAdminMainViewModel;
import com.topker.areas.product.models.viewModels.ProductShortViewModel;
import com.topker.areas.product.repositories.ProductRepository;

import com.topker.areas.productDetails.entities.ProductDetails;
import com.topker.areas.productDetails.services.ProductDetailsService;
import com.topker.areas.productStatistic.entities.ProductStatistic;
import com.topker.areas.productStatistic.services.ProductStatisticService;
import com.topker.areas.promotion.entities.Promotion;
import com.topker.areas.promotion.models.viewModels.PromotionInProductViewModel;
import com.topker.areas.promotion.services.PromotionService;
import com.topker.models.Filter;
import com.topker.utility.Calculator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;

    private final ProductRepository productRepository;

    private final PromotionService promotionService;

    private final CategoryService categoryService;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    private final BrandService brandService;

    private final ProductStatisticService productStatisticService;

    private final ProductDetailsService productDetailsService;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, PromotionService promotionService,
                              CategoryService categoryService, CategoryRepository categoryRepository, BrandRepository brandRepository, BrandService brandService,
                              ProductStatisticService productStatisticService, ProductDetailsService productDetailsService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.promotionService = promotionService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.brandService = brandService;
        this.productStatisticService = productStatisticService;
        this.productDetailsService = productDetailsService;
    }

    @Override
    public void create(SingleProductCreationModel singleProductCreationModel) {
        SingleProduct singleProduct = new SingleProduct();
        singleProduct.setModel(singleProductCreationModel.getModel());
        singleProduct.setName(singleProductCreationModel.getName());
        singleProduct.setBarcode(singleProductCreationModel.getBarcode());
        singleProduct.setMainImage(singleProductCreationModel.getMainImage());
        singleProduct.setImageTagAltAttributeText(singleProductCreationModel.getImageTagAltAttributeText());
        singleProduct.setImages(singleProductCreationModel.getImages());
        singleProduct.setImagesTagAltAttributeText(singleProductCreationModel.getImagesTagAltAttributeText());
        singleProduct.setShortDescription(singleProductCreationModel.getShortDescription());
        singleProduct.setFullDescription(singleProductCreationModel.getFullDescription());

        if (singleProductCreationModel.getBrandName() != null) {
            Brand brand = this.brandRepository.findOneByName(singleProductCreationModel.getBrandName());
            if (brand == null) {
                BrandCreationModel brandCreationModel = new BrandCreationModel();
                brandCreationModel.setName(singleProductCreationModel.getBrandName());
                brand = this.brandService.create(brandCreationModel);
            }

            singleProduct.setBrand(brand);
        }

        ProductStatistic productStatistic = new ProductStatistic();
        productStatistic.setQuantity(singleProductCreationModel.getQuantity());
        singleProduct.setProductStatistic(productStatistic);
        productStatistic.setProduct(singleProduct);

        ProductDetails productDetails = new ProductDetails();
        productDetails.setHeight(singleProductCreationModel.getHeight());
        productDetails.setLength(singleProductCreationModel.getLength());
        productDetails.setWeight(singleProductCreationModel.getWeight());
        productDetails.setWidth(singleProductCreationModel.getWidth());
        productDetails.setQuantityInPackage(singleProductCreationModel.getQuantityInPackage());
        productDetails.setSinglePrice(singleProductCreationModel.getSinglePrice());
        singleProduct.setProductDetails(productDetails);
        productDetails.setProduct(singleProduct);

        double price = productDetails.getSinglePrice() * productDetails.getQuantityInPackage();
        singleProduct.setPrice(price);

        Set<Category> categories = new HashSet<>();
        for (String categoryName : singleProductCreationModel.getCategories()) {
            Category category = this.categoryRepository.findOneByName(categoryName);
            if (category == null) {
                CategoryCreationModel categoryCreationModel = new CategoryCreationModel();
                categoryCreationModel.setName(categoryName);
                category = this.categoryService.create(categoryCreationModel);
            }

            categories.add(category);
            //category.addProduct(singleProduct);
        }

        singleProduct.setCategories(categories);

        this.productRepository.save(singleProduct);
    }

    @Override
    public void create(MultiProductCreationModel multiProductCreationModel) {
        MultiProduct multiProduct = this.modelMapper.map(multiProductCreationModel, MultiProduct.class);
        //TODO

        this.productRepository.save(multiProduct);
    }

    @Override
    public void create(SubProductCreationModel subProductCreationModel) {
        SingleProduct singleProduct = this.modelMapper.map(subProductCreationModel, SingleProduct.class);
        //TODO

        this.productRepository.save(singleProduct);
    }

    @Override
    public void updateProduct(ProductUpdateModel productUpdateModel) {
        BaseProduct product = this.productRepository.getOne(productUpdateModel.getId());
        product.setModel(productUpdateModel.getModel());
        product.setName(productUpdateModel.getName());
        product.setBarcode(productUpdateModel.getBarcode());
        product.setMainImage(productUpdateModel.getMainImage());
        product.setImageTagAltAttributeText(productUpdateModel.getImageTagAltAttributeText());
        product.setImages(productUpdateModel.getImages());
        product.setImagesTagAltAttributeText(productUpdateModel.getImagesTagAltAttributeText());
        product.setShortDescription(productUpdateModel.getShortDescription());
        product.setFullDescription(productUpdateModel.getFullDescription());

        if (!product.getBrand().getName().equals(productUpdateModel.getBrandName())) {
            Brand oldBrand = product.getBrand();
            if (productUpdateModel.getBrandName() != null) {
                Brand brand = this.brandRepository.findOneByName(productUpdateModel.getBrandName());
                if (brand == null) {
                    BrandCreationModel brandCreationModel = new BrandCreationModel();
                    brandCreationModel.setName(productUpdateModel.getBrandName());
                    brand = this.brandService.create(brandCreationModel);
                }

                product.setBrand(brand);
            } else {
                product.setBrand(null);
            }
            if (oldBrand != null) {
                oldBrand.removeProduct(product);
            }
        }

        ProductStatistic productStatistic = product.getProductStatistic();
        productStatistic.setQuantity(productUpdateModel.getQuantity());

        ProductDetails productDetails = product.getProductDetails();
        productDetails.setHeight(productUpdateModel.getHeight());
        productDetails.setLength(productUpdateModel.getLength());
        productDetails.setWeight(productUpdateModel.getWeight());
        productDetails.setWidth(productUpdateModel.getWidth());
        productDetails.setQuantityInPackage(productUpdateModel.getQuantityInPackage());
        productDetails.setSinglePrice(productUpdateModel.getSinglePrice());

        double price = productDetails.getSinglePrice() * productDetails.getQuantityInPackage();
        product.setPrice(price);

        Set<Category> categories = new HashSet<>();
        Set<Category> oldCategories = product.getCategories();
        for (String categoryName : productUpdateModel.getCategories()) {
            Category category = this.categoryRepository.findOneByName(categoryName);
            if (!category.getProducts().contains(product)) {
                category.addProduct(product);
            }
            categories.add(category);
        }

        for (Category oldCategory : oldCategories) {
            if (!product.getCategories().contains(oldCategory)) {
                oldCategory.getProducts().remove(product);
            }
        }

        product.setCategories(categories);
    }

    @Override
    public void deleteProduct(Long productId) {
        this.productRepository.delete(productId);
    }

    @Override
    public List<ProductShortViewModel> getFilteredProducts(Filter filter) {
        List<ProductShortViewModel> resultProducts = new ArrayList<>();
        //TODO


        return resultProducts;
    }

    @Override
    public List<ProductShortViewModel> getProductsOrderByPromotion() {
        List<BaseProduct> products = this.productRepository.findAll();
        List<ProductShortViewModel> resultProducts = new ArrayList<>();
        for (BaseProduct product : products) {
            if (product.getProductStatistic().getQuantity() > 0) {

                ProductShortViewModel productShortViewModel = this.modelMapper.map(product, ProductShortViewModel.class);
                double mainPrice = product.getPrice();
                int discount = 0;
                productShortViewModel.setDiscountedPrice(mainPrice);
                if (product.getActivePromotion() != null) {
                    discount = product.getActivePromotion().getDiscount();
                    double newPrice = Calculator.getDiscountedPrice(mainPrice, discount);
                    productShortViewModel.setDiscountedPrice(newPrice);
                }

                productShortViewModel.setDiscount(discount);
                boolean isOnPromotion = false;
                if (discount != 0) {
                    isOnPromotion = true;
                }

                productShortViewModel.setIsOnPromotion(isOnPromotion);
                resultProducts.add(productShortViewModel);
            }
        }

        resultProducts = resultProducts.stream()
                .sorted((f1, f2) -> Integer.compare(f2.getDiscount(), f1.getDiscount()))
                .collect(Collectors.toList());

        return resultProducts;
    }

    @Override
    public List<ProductAdminMainViewModel> getAdminMainPageProducts() {
        List<BaseProduct> products = this.productRepository.findAll();
        List<ProductAdminMainViewModel> resultProducts = new ArrayList<>();
        for (BaseProduct product : products) {
            ProductAdminMainViewModel adminViewProduct = this.modelMapper.map(product, ProductAdminMainViewModel.class);
            if (product.getBrand() != null) {
                adminViewProduct.setBrandName(product.getBrand().getName());
            }

            if (product.getActivePromotion() != null) {
                adminViewProduct.setIsOnPromotion(true);
            } else {
                adminViewProduct.setIsOnPromotion(false);
            }

            adminViewProduct.setCurrentPrice(product.getCurrentPrice());
            adminViewProduct.setQuantity(product.getProductStatistic().getQuantity());
            adminViewProduct.setQuantitySold(product.getProductStatistic().getQuantitySold());
            if (adminViewProduct.getIsSubProduct()) {
                adminViewProduct.setMainImage(product.getMainProduct().getMainImage());
            }

            resultProducts.add(adminViewProduct);
        }

        return resultProducts;
    }

    @Override
    public ProductUpdateModel getEditProduct(long productId) {
        BaseProduct product = this.productRepository.getOne(productId);
        ProductUpdateModel productUpdateModel = this.modelMapper.map(product, ProductUpdateModel.class);
        productUpdateModel.setBrandName(product.getBrand().getName());
        productUpdateModel.setQuantity(product.getProductStatistic().getQuantity());
        productUpdateModel.setHeight(product.getProductDetails().getHeight());
        productUpdateModel.setWidth(product.getProductDetails().getWidth());
        productUpdateModel.setLength(product.getProductDetails().getLength());
        productUpdateModel.setWeight(product.getProductDetails().getWeight());
        productUpdateModel.setQuantityInPackage(product.getProductDetails().getQuantityInPackage());
        productUpdateModel.setSinglePrice(product.getProductDetails().getSinglePrice());
        List<String> categoriesNames = new ArrayList<>();
        for (Category category : product.getCategories()) {
            categoriesNames.add(category.getName());
        }
        productUpdateModel.setCategories(categoriesNames);
        List<PromotionInProductViewModel> promotions = new ArrayList<>();
        for (Promotion promotion : product.getPromotions()) {
            PromotionInProductViewModel promotionViewModel = this.promotionService.getPromotionInProductViewModel(promotion.getId());
            promotions.add(promotionViewModel);
        }
        productUpdateModel.setPromotions(promotions);

        return productUpdateModel;
    }

    @Override
    public boolean isNameOccupied(String name) {
        BaseProduct product = this.productRepository.findOneByName(name);

        if (product == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isUpdatedNameValid(Long productId, String name) {
        BaseProduct product = this.productRepository.findOneByName(name);
        if (product != null && !product.getId().equals(productId)) {
            return false;
        }

        return true;
    }
}
