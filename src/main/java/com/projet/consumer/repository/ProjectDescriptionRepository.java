package com.projet.consumer.repository;


import com.projet.consumer.models.ProjectDescriptionConsumer;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProjectDescriptionRepository extends MongoRepository<ProjectDescriptionConsumer, String> {
    // You can add custom query methods if needed
}
