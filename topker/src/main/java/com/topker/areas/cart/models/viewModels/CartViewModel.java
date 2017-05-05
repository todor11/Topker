package com.topker.areas.cart.models.viewModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartViewModel implements Serializable {

    private List<Long> productsIds;

    private List<String> productNames;

    private List<Integer> productQuantities;

    public CartViewModel() {
        this.setProductNames(new ArrayList<>());
        this.setProductQuantities(new ArrayList<>());
        this.setProductsIds(new ArrayList<>());
    }

    public List<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(List<String> productNames) {
        this.productNames = productNames;
    }

    public List<Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(List<Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
