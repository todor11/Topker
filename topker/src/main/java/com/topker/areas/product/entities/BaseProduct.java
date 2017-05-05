package com.topker.areas.product.entities;

import com.topker.areas.brand.entities.Brand;
import com.topker.areas.category.entities.Category;
import com.topker.areas.productDetails.entities.ProductDetails;
import com.topker.areas.productStatistic.entities.ProductStatistic;
import com.topker.areas.promotion.entities.Promotion;
import com.topker.areas.singleProductOrder.entities.SingleProductOrder;
import com.topker.utility.Calculator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class BaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String model;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @Column(name = "price", precision = 10, scale = 2, columnDefinition="DECIMAL(10,2)")
    private double price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "base_products_promotions",
            joinColumns = @JoinColumn(name = "base_product_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    private List<Promotion> promotions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_statistic_id")
    private ProductStatistic productStatistic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_details_id")
    private ProductDetails productDetails;

    private String barcode;

    @Column(name = "main_image")
    private String mainImage;

    @Column(name = "image_tag_alt_attribute_text")
    private String imageTagAltAttributeText;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    private List<String> imagesTagAltAttributeText;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @Column(name = "is_sub_product")
    private boolean isSubProduct;

    @ManyToOne
    @JoinColumn(name = "mainProduct_id", referencedColumnName = "id")
    private MultiProduct mainProduct;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "base_products_categories",
            joinColumns = @JoinColumn(name = "base_product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SingleProductOrder> singleProductOrders;

    public BaseProduct() {
        this.setPromotions(new ArrayList<>());
        this.setImages(new ArrayList<>());
        this.setCategories(new HashSet<>());
        this.setSingleProductOrders(new ArrayList<>());
    }

    public Long getId() {
        return  this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return  this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return  this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return  this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Promotion> getPromotions() {
        return  this.promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public ProductStatistic getProductStatistic() {
        return  this.productStatistic;
    }

    public void setProductStatistic(ProductStatistic productStatistic) {
        this.productStatistic = productStatistic;
    }

    public ProductDetails getProductDetails() {
        return  this.productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public String getBarcode() {
        return  this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getMainImage() {
        return  this.mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getImages() {
        return  this.images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getShortDescription() {
        return  this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return  this.fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public double getPrice() {
        return  this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getIsSubProduct() {
        return this.isSubProduct;
    }

    public void setIsSubProduct(boolean isSubProduct) {
        this.isSubProduct = isSubProduct;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<SingleProductOrder> getSingleProductOrders() {
        return singleProductOrders;
    }

    public void setSingleProductOrders(List<SingleProductOrder> singleProductOrders) {
        this.singleProductOrders = singleProductOrders;
    }

    public String getImageTagAltAttributeText() {
        return imageTagAltAttributeText;
    }

    public void setImageTagAltAttributeText(String imageTagAltAttributeText) {
        this.imageTagAltAttributeText = imageTagAltAttributeText;
    }

    public List<String> getImagesTagAltAttributeText() {
        return imagesTagAltAttributeText;
    }

    public void setImagesTagAltAttributeText(List<String> imagesTagAltAttributeText) {
        this.imagesTagAltAttributeText = imagesTagAltAttributeText;
    }

    public MultiProduct getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(MultiProduct mainProduct) {
        this.mainProduct = mainProduct;
    }

    public Promotion getActivePromotion() {
        if (this.getPromotions() != null && !this.getPromotions().isEmpty()) {
            for (Promotion promotion : this.getPromotions()) {
                if (promotion.getStartDate().isBefore(LocalDateTime.now()) &&
                        promotion.getEndDate().isAfter(LocalDateTime.now())) {
                    return promotion;
                }
            }
        }

        return null;
    }

    public double getCurrentPrice() {
        Promotion activePromotion = this.getActivePromotion();
        if (activePromotion != null) {
            return Calculator.getDiscountedPrice(this.price, activePromotion.getDiscount());
        } else {
            return this.price;
        }
    }
}
