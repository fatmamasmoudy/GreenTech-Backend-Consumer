package com.projet.consumer.repository;

import com.projet.consumer.models.OtherLandUseChangesConsumer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtherLandUseChangesRespository extends MongoRepository<OtherLandUseChangesConsumer, Double> {
}
