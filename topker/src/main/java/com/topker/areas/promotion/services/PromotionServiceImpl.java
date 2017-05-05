package com.topker.areas.promotion.services;

import com.topker.areas.promotion.entities.Promotion;
import com.topker.areas.promotion.models.bindingModels.PromotionCreationModel;
import com.topker.areas.promotion.models.viewModels.PromotionInProductViewModel;
import com.topker.areas.promotion.repositories.PromotionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final ModelMapper modelMapper;

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionServiceImpl(ModelMapper modelMapper, PromotionRepository promotionRepository) {
        this.modelMapper = modelMapper;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void create(PromotionCreationModel promotionCreationModel) {
        Promotion promotion = this.modelMapper.map(promotionCreationModel, Promotion.class);
        //TODO

        this.promotionRepository.save(promotion);
    }

    @Override
    public PromotionInProductViewModel getPromotionInProductViewModel(Long id) {
        Promotion promotion = this.promotionRepository.findOne(id);
        return this.modelMapper.map(promotion, PromotionInProductViewModel.class);
    }
}
