package com.pluralsight.enumerations;

public enum Size {
    SMALL(24.99),
    MEDIUM(34.99),
    LARGE(44.99);

    private final double price;


    Size(double price){
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
