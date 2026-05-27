package com.pluralsight.DTOs;

import com.pluralsight.enumerations.Material;
import com.pluralsight.enumerations.Size;
import com.pluralsight.enumerations.Type;
import com.pluralsight.models.DesignCustomization;

import java.util.List;

public record HoodieDTO(
        Type type,
        Size size,
        Material material,
        List<DesignCustomization> designs,
        double price
) {
    public void getInfo(){
        
    }
}
