package com.topker.areas.product.models.viewModels;

public class ProductShortViewModel {

    private Long id;

    private String shortDescription;

    private String mainImage;

    private String imageTagAltAttributeText;

    private double price;

    private double discountedPrice;

    private int discount;

    private boolean isOnPromotion;

    public ProductShortViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean getIsOnPromotion() {
        return isOnPromotion;
    }

    public void setIsOnPromotion(boolean isOnPromotion) {
        this.isOnPromotion = isOnPromotion;
    }

    public String getImageTagAltAttributeText() {
        return imageTagAltAttributeText;
    }

    public void setImageTagAltAttributeText(String imageTagAltAttributeText) {
        this.imageTagAltAttributeText = imageTagAltAttributeText;
    }
}
