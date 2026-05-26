package com.pluralsight.enumerations;

public enum PremiumDesign {
    RHINESTONE(1.2),
    EMBROIDERY(2.0),
    RAISED(1.5);

    private final double multiplier;

    PremiumDesign(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
