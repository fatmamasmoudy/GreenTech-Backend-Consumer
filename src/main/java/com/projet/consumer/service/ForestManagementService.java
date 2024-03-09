package com.projet.consumer.service;

import com.projet.consumer.models.ForestManagementConsumer;

import java.util.List;
import java.util.Optional;

public interface ForestManagementService {
    List<ForestManagementConsumer> getAllForestManagement();

    Optional<ForestManagementConsumer> getForestManagementById(String id);
}
