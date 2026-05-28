package com.pluralsight.models;

import com.pluralsight.abstracts.OrderItem;
import com.pluralsight.enumerations.Design;
import com.pluralsight.enumerations.Material;
import com.pluralsight.interfaces.Priceable;

public class Beanie extends OrderItem implements Priceable {

    private static final double BASE_PRICE =
            14.99;

    private Material material;
    private Design design;

    public Beanie(
            Material material
    ) {

        this.material = material;
    }

    public void addDesign(
            Design design
    ) {

        this.design = design;
    }

    public void removeDesign() {

        this.design = null;
    }

    public boolean hasDesign() {

        return design != null;
    }

    @Override
    public String getDescription() {

        String description =
                material.name()
                        .toLowerCase()
                        + " beanie";

        if (design != null) {

            description +=
                    " | "
                            + design.name()
                            .toLowerCase();
        }

        return description;
    }

    @Override
    public double getPrice() {

        double base =
                BASE_PRICE
                        * material
                        .getMultiplier();

        if (design != null) {

            base +=
                    BASE_PRICE
                            * 0.25
                            * design
                            .getMultiplier();
        }

        return base;
    }
}