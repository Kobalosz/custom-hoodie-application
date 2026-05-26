package com.pluralsight.abstracts;

import com.pluralsight.interfaces.Priceable;

public abstract class OrderItem implements Priceable {
    private String name;

    // Leaving this as 'Item: [item.name]' for testing purposes.
    public String getDescription(){
        return "Item: " + name;
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
