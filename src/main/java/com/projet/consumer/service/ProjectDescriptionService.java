package com.projet.consumer.service;

import com.projet.consumer.models.ProjectDescriptionConsumer;

import java.util.List;
import java.util.Optional;

public interface ProjectDescriptionService {
    List<ProjectDescriptionConsumer> getAllProjectDescriptions();
    Optional<ProjectDescriptionConsumer> getProjectDescriptionById(String id);

}
