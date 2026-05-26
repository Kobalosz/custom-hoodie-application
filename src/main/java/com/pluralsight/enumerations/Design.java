package com.pluralsight.enumerations;

public enum Design {
    PRINTED(1.0),
    RHINESTONE(1.2),
    RAISED(1.5),
    EMBROIDERY(2.0);

    private final double multiplier;

    Design(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
