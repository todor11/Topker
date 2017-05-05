package com.topker.areas.product.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("single_product")
public class SingleProduct extends BaseProduct {

    public SingleProduct() {
    }
}
