package com.projet.consumer.service;

import com.projet.consumer.models.DeforestationConsumer;

import java.util.List;
import java.util.Optional;

public interface DeforestationService {

    List<DeforestationConsumer> getAllDeforestation();
    Optional<DeforestationConsumer> getDeforestationById(String id);
}
