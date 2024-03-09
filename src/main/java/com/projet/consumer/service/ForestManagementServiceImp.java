package com.projet.consumer.service;

import com.projet.consumer.models.ForestManagementConsumer;
import com.projet.consumer.models.ProjectDescriptionConsumer;
import com.projet.consumer.repository.ForestMangementRepository;
import com.projet.consumer.repository.ProjectDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForestManagementServiceImp implements ForestManagementService{

    @Autowired
    ForestMangementRepository forestMangementRepository;


    @Override
    public List<ForestManagementConsumer> getAllForestManagement() {
        return forestMangementRepository.findAll();
    }

    @Override
    public Optional<ForestManagementConsumer> getForestManagementById(String id) {
        return forestMangementRepository.findById(id);
    }

}
