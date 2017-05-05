package com.topker.areas.promotion.services;

import com.topker.areas.promotion.models.bindingModels.PromotionCreationModel;
import com.topker.areas.promotion.models.viewModels.PromotionInProductViewModel;

public interface PromotionService {

    void create(PromotionCreationModel promotionCreationModel);

    PromotionInProductViewModel getPromotionInProductViewModel(Long id);
}
