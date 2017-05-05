package com.topker.areas.simpleProductDetail.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "simple_string_product_details")
@PrimaryKeyJoinColumn(name = "id")
public class StringValueSimpleProductDetail extends SimpleProductDetail<String> {

    private String value;

    public StringValueSimpleProductDetail() {
    }

    public StringValueSimpleProductDetail(String name) {
        super(name);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
