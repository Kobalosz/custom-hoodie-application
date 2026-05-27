package com.pluralsight.models;

import com.pluralsight.abstracts.OrderItem;
import com.pluralsight.enumerations.*;
import com.pluralsight.interfaces.Priceable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Hoodie extends OrderItem implements Priceable {
    private Type type;
    private Size size;
    private List<Material> materials = new ArrayList<>();
    private List<DesignCustomization> designs = new ArrayList<>();

    public Hoodie(Type type, Size size, List<Material> materials, List<DesignCustomization> designs) {
        this.type = type;
        this.size = size;
        this.materials = materials;
        this.designs = designs;
    }

    public void addDesign(Design design, DesignLocation location){
        if (design == null || location == null) {
            throw new IllegalArgumentException("Design and location weren't entered properly");
        }

        DesignCustomization customization = new DesignCustomization(
                design,
                location
        );

        if(designs.contains(customization)){
            throw new IllegalArgumentException("There is already a design here!");
        }

        designs.add(
                customization
        );
    }

    public boolean removeDesign(Design design, DesignLocation location){
        if (design == null || location == null) {
            throw new IllegalArgumentException("Design and location weren't entered properly");
        }

        DesignCustomization target = designs.stream()
                .filter(designCustomization -> designCustomization.getDesign() == design && designCustomization.getLocation() == location)
                .findFirst()
                .orElse(null);

        if(target == null){
            return false;
        }

        designs.remove(target);
        return true;
    }
}
