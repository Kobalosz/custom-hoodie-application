package com.pluralsight.enumerations;

public enum Material {
    COTTON(1.0),
    POLYESTER(1.0),
    HEAVYWEIGHT(1.2),
    FLEECE(1.5),
    WOOL(2.0);

    private final double multiplier;

    Material(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
