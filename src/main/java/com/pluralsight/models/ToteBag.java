package com.pluralsight.models;


import com.pluralsight.abstracts.OrderItem;
import com.pluralsight.interfaces.Priceable;

public class ToteBag extends OrderItem implements Priceable {

    private static final double PRICE =
            19.99;

    @Override
    public String getDescription() {

        return "Canvas Tote Bag";
    }

    @Override
    public double getPrice() {

        return PRICE;
    }
}