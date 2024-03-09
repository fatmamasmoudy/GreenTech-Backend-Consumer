package com.projet.consumer.service;

import com.projet.consumer.models.ForestManagementConsumer;
import com.projet.consumer.models.GrasslandConsumer;
import com.projet.consumer.models.ProjectDescriptionConsumer;
import com.projet.consumer.repository.ForestMangementRepository;
import com.projet.consumer.repository.GrassLandRepository;
import com.projet.consumer.repository.ProjectDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GrassLandServiceImpl implements GrassLandService {


        @Autowired
        GrassLandRepository grassLandRepository;


        @Override
        public List<GrasslandConsumer> getAllGrassLand() {
            return grassLandRepository.findAll();
        }

        @Override
        public Optional<GrasslandConsumer> getGrassLandById(String id) {
            return grassLandRepository.findById(id);
        }

    }

