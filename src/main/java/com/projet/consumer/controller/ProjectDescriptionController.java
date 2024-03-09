package com.projet.consumer.controller;


import com.projet.consumer.models.ProjectDescriptionConsumer;
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
@RequestMapping("/api/project-descriptions")
public class ProjectDescriptionController {
    @Autowired
    ProjectDescriptionServiceImpl projectDescriptionService ;



    @GetMapping
    public ResponseEntity<List<ProjectDescriptionConsumer>> getAllProjectDescriptions() {
        List<ProjectDescriptionConsumer> projectDescriptions = projectDescriptionService.getAllProjectDescriptions();
        return new ResponseEntity<>(projectDescriptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDescriptionConsumer> getProjectDescriptionById(@PathVariable("id") String id) {
        Optional<ProjectDescriptionConsumer> projectDescription = projectDescriptionService.getProjectDescriptionById(id);
        return projectDescription.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
