package com.topker.areas.singleProductOrder.models.bindingModels;

import com.topker.areas.order.entities.Order;
import com.topker.areas.product.entities.BaseProduct;

public class SingleProductOrderCreationModel {

    private BaseProduct product;

    private int orderedQuantity;

    private double singleProductPresentPrice;

    private Order order;
}
