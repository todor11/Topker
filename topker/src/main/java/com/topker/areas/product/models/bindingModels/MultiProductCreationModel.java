package com.topker.areas.product.models.bindingModels;

import java.util.ArrayList;
import java.util.List;

public class MultiProductCreationModel extends SingleProductCreationModel {

    private List<SubProductCreationModel> subProducts;

    public MultiProductCreationModel() {
        this.setSubProducts(new ArrayList<>());
    }

    public List<SubProductCreationModel> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(List<SubProductCreationModel> subProducts) {
        this.subProducts = subProducts;
    }
}
