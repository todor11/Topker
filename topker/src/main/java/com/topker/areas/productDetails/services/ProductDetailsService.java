package com.topker.areas.productDetails.services;

import com.topker.areas.productDetails.entities.ProductDetails;
import com.topker.areas.productDetails.models.bindingModels.ProductDetailsCreationModel;

public interface ProductDetailsService{

    ProductDetails create(ProductDetailsCreationModel productDetailsCreationModel);
}
