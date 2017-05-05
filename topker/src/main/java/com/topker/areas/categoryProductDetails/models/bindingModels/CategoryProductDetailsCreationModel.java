package com.topker.areas.categoryProductDetails.models.bindingModels;

import com.topker.areas.simpleProductDetail.models.bindingModels.SimpleProductDetailCreationModel;

import java.util.List;

public class CategoryProductDetailsCreationModel {

    private String name;

    private List<SimpleProductDetailCreationModel> simpleProductDetails;

    public CategoryProductDetailsCreationModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SimpleProductDetailCreationModel> getSimpleProductDetails() {
        return simpleProductDetails;
    }

    public void setSimpleProductDetails(List<SimpleProductDetailCreationModel> simpleProductDetails) {
        this.simpleProductDetails = simpleProductDetails;
    }
}
