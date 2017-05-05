package com.topker.areas.brand.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

public class BrandCreationModel {

    @NotEmpty(message = "")// go to mess.properties
    private String name;

    public BrandCreationModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
