package com.company.service;

public class Checker {
    public static final double PRICE_MIN = 0.0001;
    public static final double PRICE_MAX = 10e10;

    public static void checkPriceRange(double price) {
        if (price < PRICE_MIN) {
            throw new IllegalArgumentException("Price is very small: " + price);
        }
        if (price > PRICE_MAX) {
            throw new IllegalArgumentException("Price is very big: " + price);
        }
    }
}
