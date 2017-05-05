package com.topker.areas.brand.services;

import com.topker.areas.brand.entities.Brand;
import com.topker.areas.brand.models.bindingModels.BrandCreationModel;
import com.topker.areas.brand.models.viewModels.BrandNameViewModel;
import com.topker.areas.brand.repositories.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final ModelMapper modelMapper;

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand create(BrandCreationModel brandCreationModel) {
        Brand brand = this.modelMapper.map(brandCreationModel, Brand.class);
        return this.brandRepository.save(brand);
    }

    @Override
    public boolean isBrandNameTaken(String name) {
        Brand brand = this.brandRepository.findOneByName(name);
        if (brand == null) {
            return false;
        }

        return true;
    }

    @Override
    public List<BrandNameViewModel> getAllBrands() {
        List<Brand> brands = this.brandRepository.findAll();
        List<BrandNameViewModel> resultBrands = new ArrayList<>();
        for (Brand brand : brands) {
            BrandNameViewModel brandNameViewModel = this.modelMapper.map(brand, BrandNameViewModel.class);
            resultBrands.add(brandNameViewModel);
        }

        return resultBrands;
    }
}
