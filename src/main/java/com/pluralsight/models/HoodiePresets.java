package com.pluralsight.models;

import com.pluralsight.enumerations.*;

import java.util.ArrayList;

public class HoodiePresets {

    public static Hoodie classicPullover() {

        return new Hoodie(
                Type.PULLOVER,
                Size.MEDIUM,
                Material.COTTON,
                new ArrayList<>()
        );
    }

    public static Hoodie premiumStreetwear() {

        Hoodie hoodie =
                new Hoodie(
                        Type.ZIPUP,
                        Size.LARGE,
                        Material.FLEECE,
                        new ArrayList<>()
                );

        hoodie.addDesign(
                Design.EMBROIDERY,
                DesignLocation.FRONT
        );

        return hoodie;
    }

    public static Hoodie winterHeavyweight() {

        Hoodie hoodie =
                new Hoodie(
                        Type.PULLOVER,
                        Size.LARGE,
                        Material.HEAVYWEIGHT,
                        new ArrayList<>()
                );

        hoodie.addDesign(
                Design.RAISED,
                DesignLocation.BACK
        );

        return hoodie;
    }

    public static Hoodie luxuryEdition() {

        Hoodie hoodie =
                new Hoodie(
                        Type.QUARTER,
                        Size.MEDIUM,
                        Material.WOOL,
                        new ArrayList<>()
                );

        hoodie.addDesign(
                Design.RHINESTONE,
                DesignLocation.FRONT
        );

        hoodie.addDesign(
                Design.EMBROIDERY,
                DesignLocation.HOOD
        );

        return hoodie;
    }
}