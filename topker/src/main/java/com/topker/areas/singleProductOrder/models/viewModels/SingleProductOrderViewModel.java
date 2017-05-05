package com.topker.areas.singleProductOrder.models.viewModels;

public class SingleProductOrderViewModel {

    private Long id;

    private String productName;

    private int orderedQuantity;

    private double singleProductPresentPrice;

    public SingleProductOrderViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public double getSingleProductPresentPrice() {
        return singleProductPresentPrice;
    }

    public void setSingleProductPresentPrice(double singleProductPresentPrice) {
        this.singleProductPresentPrice = singleProductPresentPrice;
    }
}
