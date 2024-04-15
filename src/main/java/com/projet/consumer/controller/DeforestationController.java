package com.projet.consumer.controller;


import com.projet.consumer.models.DeforestationConsumer;
import com.projet.consumer.service.DeforestationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/deforestation")

public class DeforestationController {

    @Autowired
    DeforestationServiceImp deforestationServiceImp;

    @GetMapping
    public ResponseEntity<List<DeforestationConsumer>> getAllDeforestation() {
        List<DeforestationConsumer> deforestation = deforestationServiceImp.getAllDeforestation();
        return new ResponseEntity<>(deforestation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeforestationConsumer> getDeforestationById(@PathVariable("id") String id) {
        Optional<DeforestationConsumer> deforestation = deforestationServiceImp.getDeforestationById(id);
        return deforestation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
