package com.topker.enums;

public enum OrderStatus {

    NOT_CONFIRMED ("orderStatus.notConfirmed"),
    PROCESSING ("orderStatus.processing"),
    APPROVED ("orderStatus.approved"),
    SHIPPED ("orderStatus.shipped"),
    DELIVERED("orderStatus.delivered");

    private String userMessageObj;

    OrderStatus(String userMessageObj) {
        this.userMessageObj = userMessageObj;
    }

    public String getUserMessageObj() {
        return userMessageObj;
    }
}
