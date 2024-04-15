package com.projet.consumer.service;

import com.projet.consumer.models.DeforestationConsumer;
import com.projet.consumer.repository.DeforestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DeforestationServiceImp implements DeforestationService{

    @Autowired
    DeforestationRepository deforestationRepository;


    @Override
    public List<DeforestationConsumer> getAllDeforestation() {
        return deforestationRepository.findAll();
    }

    @Override
    public Optional<DeforestationConsumer> getDeforestationById(String id) {
        return deforestationRepository.findById(id);
    }

}
