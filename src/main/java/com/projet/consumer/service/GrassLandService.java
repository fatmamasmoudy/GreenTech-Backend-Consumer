package com.projet.consumer.service;

import com.projet.consumer.models.GrasslandConsumer;

import java.util.List;
import java.util.Optional;



public interface GrassLandService {
    List<GrasslandConsumer> getAllGrassLand();
    Optional<GrasslandConsumer> getGrassLandById(String id);

}
