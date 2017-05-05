package com.topker.areas.simpleProductDetail.models.viewModels;

public class SimpleBooleanProductDetailViewModel extends SimpleProductDetailViewModel<Boolean> {

    public SimpleBooleanProductDetailViewModel() {
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }
}
