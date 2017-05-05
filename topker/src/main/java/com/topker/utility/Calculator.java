package com.topker.utility;

public class Calculator {

    public static double getDiscountedPrice(double price, int discount) {
        double newPrice = (price / 100) * (100 - discount);
        return Math.round(newPrice * 100.0) / 100.0;
    }
}
