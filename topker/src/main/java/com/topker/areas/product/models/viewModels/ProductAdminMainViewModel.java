package com.topker.areas.product.models.viewModels;

public class ProductAdminMainViewModel {

    private Long id;

    private String barcode;

    private String model;

    private String name;

    private String brandName;

    private double price;

    private double currentPrice;

    private boolean isOnPromotion;

    private long quantity;

    private long quantitySold;

    private String mainImage;

    private String shortDescription;

    private boolean isSubProduct;

    public ProductAdminMainViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean getIsOnPromotion() {
        return isOnPromotion;
    }

    public void setIsOnPromotion(boolean isOnPromotion) {
        this.isOnPromotion = isOnPromotion;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(long quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public boolean getIsSubProduct() {
        return isSubProduct;
    }

    public void setIsSubProduct(boolean isSubProduct) {
        this.isSubProduct = isSubProduct;
    }
}
