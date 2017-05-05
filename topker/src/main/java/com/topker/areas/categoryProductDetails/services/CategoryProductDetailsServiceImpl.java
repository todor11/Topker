package com.topker.areas.categoryProductDetails.services;

import com.topker.areas.categoryProductDetails.entities.CategoryProductDetails;
import com.topker.areas.categoryProductDetails.models.bindingModels.CategoryProductDetailsCreationModel;
import com.topker.areas.categoryProductDetails.repositories.CategoryProductDetailsRepository;
import com.topker.areas.simpleProductDetail.entities.SimpleProductDetail;
import com.topker.areas.simpleProductDetail.models.bindingModels.SimpleProductDetailCreationModel;
import com.topker.areas.simpleProductDetail.services.SimpleProductDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryProductDetailsServiceImpl implements CategoryProductDetailsService {

    private final ModelMapper modelMapper;

    private final CategoryProductDetailsRepository categoryProductDetailsRepository;

    private final SimpleProductDetailService simpleProductDetailService;

    @Autowired
    public CategoryProductDetailsServiceImpl(ModelMapper modelMapper, CategoryProductDetailsRepository categoryProductDetailsRepository,
                                             SimpleProductDetailService simpleProductDetailService) {
        this.modelMapper = modelMapper;
        this.categoryProductDetailsRepository = categoryProductDetailsRepository;
        this.simpleProductDetailService = simpleProductDetailService;
    }


    @Override
    public CategoryProductDetails create(CategoryProductDetailsCreationModel categoryProductDetailsCreationModel) {
        CategoryProductDetails categoryProductDetails = this.modelMapper.map(categoryProductDetailsCreationModel, CategoryProductDetails.class);
        List<SimpleProductDetailCreationModel> simpleProductDetailCreationModels = categoryProductDetailsCreationModel.getSimpleProductDetails();
        for (SimpleProductDetailCreationModel simpleProductDetailCreationModel : simpleProductDetailCreationModels) {
            SimpleProductDetail simpleProductDetail = this.simpleProductDetailService.create(simpleProductDetailCreationModel);
            categoryProductDetails.addSimpleProductDetail(simpleProductDetail);
        }

        return this.categoryProductDetailsRepository.save(categoryProductDetails);
    }
}
