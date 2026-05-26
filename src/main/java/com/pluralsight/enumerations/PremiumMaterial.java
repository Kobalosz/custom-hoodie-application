package com.pluralsight.enumerations;

public enum PremiumMaterial {
    HEAVYWEIGHT(1.2),
    FLEECE(1.5),
    WOOL(2.0);

    private final double multiplier;

    PremiumMaterial(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
