package com.topker.areas.simpleProductDetail.models.viewModels;

public abstract class SimpleProductDetailViewModel <T>{

    protected String name;

    protected T value;

    public SimpleProductDetailViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
