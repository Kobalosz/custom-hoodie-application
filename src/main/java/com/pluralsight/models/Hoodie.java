package com.pluralsight.models;

import com.pluralsight.DTOs.HoodieDTO;
import com.pluralsight.abstracts.OrderItem;
import com.pluralsight.enumerations.*;
import com.pluralsight.interfaces.Priceable;

import java.util.ArrayList;
import java.util.List;


public class Hoodie extends OrderItem implements Priceable {
    private Type type;
    private Size size;
    private Material material;
    private List<DesignCustomization> designs = new ArrayList<>();

    public Hoodie(Type type, Size size, Material material, List<DesignCustomization> designs) {
        this.type = type;
        this.size = size;
        this.material = material;
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
    // If I want to replace a design I'll just use those two methods rather than creating a whole new one



    public HoodieDTO getInfo(){
        return new HoodieDTO(
                type,
                size,
                material,
                List.copyOf(designs),
                getPrice()
        );
    }

    @Override
    public String getDescription() {
        String hoodieDesigns = designs.isEmpty() ? "No Designs" : designs.stream()
                                                                  .map(d-> d.getDesign() + " (" + d.getLocation() + ")")
                                                                  .reduce((a,b)-> a + ", " + b).orElse("");
        return String.format(
                "%s %s %s Hoodie | %s",
                size,
                material,
                type,
                hoodieDesigns
        );
    }

    @Override
    public double getPrice() {

        double basePrice = size.getPrice() * material.getMultiplier();

        double designCost = designs.stream().mapToDouble(designCustomization -> {
            double designPrice = size.getPrice() * 0.25 * designCustomization.getDesign().getMultiplier();
            return designPrice;
        }).sum();

        return basePrice + designCost;
    }
    // Finish building out the Order (Contains all order details), Build out my UI class,
}
