package com.topker.areas.product.models.bindingModels;

import com.topker.areas.categoryProductDetails.models.bindingModels.CategoryProductDetailsCreationModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

public class SingleProductCreationModel {

    private String model;

    @NotEmpty(message = "errorMessageSingleProductCreate.name")
    private String name;

    private String brandName;

    @Min(value = 0, message = "errorMessageSingleProductCreate.quantity")
    private long quantity;

    private double height;

    private double width;

    private double length;

    private double weight;

    @Min(value = 1, message = "errorMessageSingleProductCreate.quantityInPackage")
    private int quantityInPackage;

    @DecimalMin(value="0", inclusive=false, message = "errorMessageSingleProductCreate.singlePrice")
    private double singlePrice;

    private String barcode;

    private String mainImage;

    private String imageTagAltAttributeText;

    private List<String> images;

    private List<String> imagesTagAltAttributeText;

    private String shortDescription;

    private String fullDescription;

    private List<String> categories;

    private List<CategoryProductDetailsCreationModel> categoryProductDetails;

    public SingleProductCreationModel() {
        this.setImages(new ArrayList<>());
        this.setCategories(new ArrayList<>());
        this.setImagesTagAltAttributeText(new ArrayList<>());
        this.setCategoryProductDetails(new ArrayList<>());
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantityInPackage() {
        return quantityInPackage;
    }

    public void setQuantityInPackage(int quantityInPackage) {
        this.quantityInPackage = quantityInPackage;
    }

    public double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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

    public List<CategoryProductDetailsCreationModel> getCategoryProductDetails() {
        return categoryProductDetails;
    }

    public void setCategoryProductDetails(List<CategoryProductDetailsCreationModel> categoryProductDetails) {
        this.categoryProductDetails = categoryProductDetails;
    }
}
