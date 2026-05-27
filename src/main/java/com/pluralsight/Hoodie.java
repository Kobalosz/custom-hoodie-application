package com.pluralsight;

import com.pluralsight.abstracts.OrderItem;
import com.pluralsight.enumerations.Design;
import com.pluralsight.enumerations.Material;
import com.pluralsight.enumerations.Size;
import com.pluralsight.enumerations.Type;
import com.pluralsight.interfaces.Priceable;

import java.util.ArrayList;
import java.util.List;

public class Hoodie extends OrderItem implements Priceable {
    private Type type;
    private Size size;
    private List<Material> materials = new ArrayList<>();
    private List<Design> designs = new ArrayList<>();

    public Hoodie(Type type, Size size, List<Material> materials, List<Design> designs) {
        this.type = type;
        this.size = size;
        this.materials = materials;
        this.designs = designs;
    }
}
