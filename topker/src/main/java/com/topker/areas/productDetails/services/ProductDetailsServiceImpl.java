package com.topker.areas.productDetails.services;

import com.topker.areas.productDetails.entities.ProductDetails;
import com.topker.areas.productDetails.models.bindingModels.ProductDetailsCreationModel;
import com.topker.areas.productDetails.repositories.ProductDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ModelMapper modelMapper;

    private final ProductDetailsRepository productDetailsRepository;

    @Autowired
    public ProductDetailsServiceImpl(ModelMapper modelMapper, ProductDetailsRepository productDetailsRepository) {
        this.modelMapper = modelMapper;
        this.productDetailsRepository = productDetailsRepository;
    }

    @Override
    public ProductDetails create(ProductDetailsCreationModel productDetailsCreationModel) {
        ProductDetails productDetails = this.modelMapper.map(productDetailsCreationModel, ProductDetails.class);

        return this.productDetailsRepository.save(productDetails);
    }
}
