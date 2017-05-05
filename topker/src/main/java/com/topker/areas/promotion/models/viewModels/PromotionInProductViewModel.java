package com.topker.areas.promotion.models.viewModels;

import java.time.LocalDateTime;

public class PromotionInProductViewModel {

    private Long id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int discount;

    public PromotionInProductViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean getIsActivePromotion() {
        if (this.startDate.isBefore(LocalDateTime.now()) && this.endDate.isAfter(LocalDateTime.now())) {
            return true;
        }

        return false;
    }

    public boolean getIsFinishedPromotion() {
        if (this.startDate.isBefore(LocalDateTime.now()) && this.endDate.isBefore(LocalDateTime.now())) {
            return true;
        }

        return false;
    }

    public boolean getIsNotStartedPromotion() {
        if (this.startDate.isAfter(LocalDateTime.now()) && this.endDate.isAfter(LocalDateTime.now())) {
            return true;
        }

        return false;
    }
}
