package com.topker.areas.categoryProductDetails.services;

import com.topker.areas.categoryProductDetails.entities.CategoryProductDetails;
import com.topker.areas.categoryProductDetails.models.bindingModels.CategoryProductDetailsCreationModel;

public interface CategoryProductDetailsService {

    CategoryProductDetails create(CategoryProductDetailsCreationModel categoryProductDetailsCreationModel);
}
