package com.topker.areas.productStatistic.services;

import com.topker.areas.productStatistic.entities.ProductStatistic;
import com.topker.areas.productStatistic.models.bindingModels.ProductStatisticCreationModel;

public interface ProductStatisticService {

    ProductStatistic create(ProductStatisticCreationModel productStatisticCreationModel);
}
