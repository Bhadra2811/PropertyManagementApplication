package org.example.homework.controller;

import org.example.homework.model.Property;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    private final List<Property> properties = Arrays.asList(
            new Property() {{ setId(1); setAddress("123 Main St"); setCity("Springfield"); setState("IL"); setPrice(250000.0); setAreaInSqFt(1500.0); }},
            new Property() {{ setId(2); setAddress("456 Elm St"); setCity("Columbus"); setState("OH"); setPrice(300000.0); setAreaInSqFt(2000.0); }}
    );

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Integer id) {
        return properties.stream()
                .filter(property -> property.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

