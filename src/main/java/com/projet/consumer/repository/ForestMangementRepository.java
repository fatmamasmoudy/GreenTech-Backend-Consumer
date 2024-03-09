package com.projet.consumer.repository;

import com.projet.consumer.models.ForestManagementConsumer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForestMangementRepository extends MongoRepository< ForestManagementConsumer, String>{
}
