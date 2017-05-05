package com.topker.areas.singleProductOrder.services;

import com.topker.areas.singleProductOrder.entities.SingleProductOrder;
import com.topker.areas.singleProductOrder.models.bindingModels.SingleProductOrderCreationModel;

public interface SingleProductOrderService {

    SingleProductOrder create(SingleProductOrderCreationModel singleProductOrderCreationModel);
}
