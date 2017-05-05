package com.topker.areas.category.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

public class CategoryCreationModel {

    @NotEmpty(message = "")// go to mess.properties
    private String name;

    public CategoryCreationModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
