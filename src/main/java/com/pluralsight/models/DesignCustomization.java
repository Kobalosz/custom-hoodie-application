package com.pluralsight.models;

import com.pluralsight.enumerations.Design;
import com.pluralsight.enumerations.DesignLocation;

public class DesignCustomization {
    private Design design;
    private DesignLocation location;

    public DesignCustomization(Design design, DesignLocation location){
        this.design = design;
        this.location = location;
    }

    public Design getDesign() {
        return design;
    }

    public DesignLocation getLocation() {
        return location;
    }
}
