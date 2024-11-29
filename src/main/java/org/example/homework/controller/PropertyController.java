package org.example.homework.controller;

import org.example.homework.model.Property;
import org.example.homework.model.PropertyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    // Simulating a simple database with a hardcoded list of properties
    private List<Property> propertyList = new ArrayList<>();

    public PropertyController() {
        // Adding some initial properties for testing
        propertyList.add(new Property(1, "123 Main St", "New York", "NY", 500000.0, 2000.0));
        propertyList.add(new Property(2, "456 Oak St", "Los Angeles", "CA", 350000.0, 1500.0));
        propertyList.add(new Property(3, "789 Pine St", "Chicago", "IL", 450000.0, 1800.0));
        propertyList.add(new Property(4, "101 Maple St", "Houston", "TX", 400000.0, 1600.0));
        propertyList.add(new Property(5, "202 Birch St", "Miami", "FL", 600000.0, 2200.0));
    }

    // GET API: Fetch property by ID
    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Integer id) {
        Optional<Property> property = propertyList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (property.isPresent()) {
            return property.get();
        } else {
            throw new PropertyNotFoundException("Property not found with id: " + id);
        }

    }

    // PUT API: Update property by ID
    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable Integer id, @RequestBody Property updatedProperty) {
        for (Property property : propertyList) {
            if (property.getId().equals(id)) {
                // Validate data before updating
                if (updatedProperty.getPrice() <= 0 || updatedProperty.getAreaInSqFt() <= 0) {
                    throw new IllegalArgumentException("Price and Area must be positive numbers.");
                }

                // Update the property
                property.setAddress(updatedProperty.getAddress());
                property.setCity(updatedProperty.getCity());
                property.setState(updatedProperty.getState());
                property.setPrice(updatedProperty.getPrice());
                property.setAreaInSqFt(updatedProperty.getAreaInSqFt());

                return property;
            }
        }
        throw new PropertyNotFoundException("Property not found with id: " + id);
    }
}
