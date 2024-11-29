package org.example.homework.controller;

import org.example.homework.model.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PropertyController {

    private List<Property> propertyList = new ArrayList<>();

    // Add hardcoded properties (for GET API)
    public PropertyController() {
        propertyList.add(new Property(1, "123 Main St", "Springfield", "IL", 250000.00, 2000.0));
        propertyList.add(new Property(2, "456 Elm St", "Chicago", "IL", 300000.00, 2500.0));
        propertyList.add(new Property(3, "789 Oak St", "Peoria", "IL", 180000.00, 1800.0));
        propertyList.add(new Property(4, "101 Pine St", "Canton", "IL", 220000.00, 2200.0));
        propertyList.add(new Property(5, "202 Maple St", "Champaign", "IL", 350000.00, 2800.0));
    }

    // DELETE API to remove a property by id
    @DeleteMapping("/properties/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Integer id) {
        Property property = propertyList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (property == null) {
            return new ResponseEntity<>("Property not found", HttpStatus.NOT_FOUND); // Return 404 if property is not found
        }

        propertyList.remove(property); // Remove the property from the list
        return new ResponseEntity<>("Property deleted successfully", HttpStatus.OK); // Return 200 if property is deleted
    }
}
