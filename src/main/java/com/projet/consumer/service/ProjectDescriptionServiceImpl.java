package com.projet.consumer.service;
import com.projet.consumer.models.ProjectDescriptionConsumer;
import com.projet.consumer.repository.ProjectDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectDescriptionServiceImpl implements ProjectDescriptionService {

    @Autowired
    ProjectDescriptionRepository projectDescriptionRepository;


    @Override
    public List<ProjectDescriptionConsumer> getAllProjectDescriptions() {
        return projectDescriptionRepository.findAll();
    }

    @Override
    public Optional<ProjectDescriptionConsumer> getProjectDescriptionById(String id) {
        return projectDescriptionRepository.findById(id);
    }

}
