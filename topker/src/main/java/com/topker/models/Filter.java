package com.topker.models;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    private List<String> categories;

    private List<String> brands;

    private List<String> priceRanges;

    public Filter() {
        this.setCategories(new ArrayList<>());
        this.setBrands(new ArrayList<>());
        this.setPriceRanges(new ArrayList<>());
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public List<String> getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(List<String> priceRanges) {
        this.priceRanges = priceRanges;
    }
}
