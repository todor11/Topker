package com.topker.areas.simpleProductDetail.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "simple_boolean_product_details")
@PrimaryKeyJoinColumn(name = "id")
public class BooleanValueSimpleProductDetail extends SimpleProductDetail<Boolean> {

    private boolean value;

    public BooleanValueSimpleProductDetail() {
    }

    public BooleanValueSimpleProductDetail(String name) {
        super(name);
    }

    @Override
    public Boolean getValue() {
        return null;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}
