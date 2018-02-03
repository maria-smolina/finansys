package com.company.model;

import com.company.model.Security;

public class SecurityMock implements Security {
    private double price = 0.0;

    public SecurityMock() {
    }

    public SecurityMock(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
