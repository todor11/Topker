package com.topker.areas.productStatistic.services;

import com.topker.areas.productStatistic.entities.ProductStatistic;
import com.topker.areas.productStatistic.models.bindingModels.ProductStatisticCreationModel;
import com.topker.areas.productStatistic.repositories.ProductStatisticRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductStatisticServiceImpl implements ProductStatisticService {

    private final ModelMapper modelMapper;

    private final ProductStatisticRepository productStatisticRepository;

    @Autowired
    public ProductStatisticServiceImpl(ModelMapper modelMapper, ProductStatisticRepository productStatisticRepository) {
        this.modelMapper = modelMapper;
        this.productStatisticRepository = productStatisticRepository;
    }

    @Override
    public ProductStatistic create(ProductStatisticCreationModel productStatisticCreationModel) {
        ProductStatistic productStatistic = this.modelMapper.map(productStatisticCreationModel, ProductStatistic.class);
        productStatistic.setQuantitySold(0);

        return this.productStatisticRepository.save(productStatistic);
    }
}
