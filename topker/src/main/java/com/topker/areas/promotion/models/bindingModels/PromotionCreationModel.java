package com.topker.areas.promotion.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

public class PromotionCreationModel {

    @NotEmpty(message = "")//go to mess.props
    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    //validate
    private double discount;

    public PromotionCreationModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
