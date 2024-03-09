package com.projet.consumer.repository;

import com.projet.consumer.models.ForestManagementConsumer;
import com.projet.consumer.models.GrasslandConsumer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrassLandRepository extends MongoRepository<GrasslandConsumer, String> {

}
