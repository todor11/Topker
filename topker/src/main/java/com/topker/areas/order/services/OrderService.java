package com.topker.areas.order.services;

import com.topker.areas.order.models.bindingModels.OrderCreationModel;

public interface OrderService {

    void create(OrderCreationModel orderCreationModel);
}
