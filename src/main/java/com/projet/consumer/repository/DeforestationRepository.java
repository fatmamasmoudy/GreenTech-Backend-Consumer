package com.projet.consumer.repository;

import com.projet.consumer.models.DeforestationConsumer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface DeforestationRepository extends MongoRepository<DeforestationConsumer, String> {
}
