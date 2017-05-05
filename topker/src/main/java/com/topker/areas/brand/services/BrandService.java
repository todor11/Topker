package com.topker.areas.brand.services;

import com.topker.areas.brand.entities.Brand;
import com.topker.areas.brand.models.bindingModels.BrandCreationModel;
import com.topker.areas.brand.models.viewModels.BrandNameViewModel;

import java.util.List;

public interface BrandService {

    Brand create(BrandCreationModel brandCreationModel);

    boolean isBrandNameTaken(String name);

    List<BrandNameViewModel> getAllBrands();
}
