package com.topker.areas.simpleProductDetail.models.viewModels;

public class SimpleStringProductDetailViewModel  extends SimpleProductDetailViewModel<String> {

    public SimpleStringProductDetailViewModel() {
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
