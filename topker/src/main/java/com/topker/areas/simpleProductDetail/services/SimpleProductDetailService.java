package com.topker.areas.simpleProductDetail.services;

import com.topker.areas.simpleProductDetail.entities.SimpleProductDetail;
import com.topker.areas.simpleProductDetail.models.bindingModels.SimpleProductDetailCreationModel;
import com.topker.areas.simpleProductDetail.models.viewModels.SimpleProductDetailViewModel;

import java.util.List;

public interface SimpleProductDetailService {

    SimpleProductDetail create(SimpleProductDetailCreationModel simpleProductDetailCreationModel);

    List<SimpleProductDetailViewModel> getAllSimpleProductDetailsByCategoryProductDetailId(Long categoryProductDetailsId);
}
