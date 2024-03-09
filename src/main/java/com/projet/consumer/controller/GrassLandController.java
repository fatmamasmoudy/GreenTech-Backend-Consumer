package com.projet.consumer.controller;
import com.projet.consumer.models.GrasslandConsumer;
import com.projet.consumer.models.ProjectDescriptionConsumer;
import com.projet.consumer.service.GrassLandServiceImpl;
import com.projet.consumer.service.ProjectDescriptionService;
import com.projet.consumer.service.ProjectDescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/grass-land")

public class GrassLandController {

    @Autowired
    GrassLandServiceImpl grassLandService ;

    @GetMapping
    public ResponseEntity<List<GrasslandConsumer>> getAllGrassLands() {
        List<GrasslandConsumer> grasslandConsumers = grassLandService.getAllGrassLand();
        return new ResponseEntity<>(grasslandConsumers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrasslandConsumer> getGrassLandsById(@PathVariable("id") String id) {
        Optional<GrasslandConsumer> grasslandConsumer = grassLandService.getGrassLandById(id);
        return grasslandConsumer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
