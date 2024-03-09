package com.projet.consumer.service;

import com.projet.consumer.models.ForestManagementConsumer;
import com.projet.consumer.models.dto.ForestManagement;
import com.projet.consumer.repository.ForestMangementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaConsumerForestService {

    @Autowired
    ForestMangementRepository forestMangementRepository;


    //@KafkaListener(topics = {"Land-use-topic"}, groupId = "group-id")
    @KafkaListener(topics = {"forest-management-topic"}, groupId = "group-id3" , containerFactory = "kafkaListenerContainerForestFactory")
    public void consume( ForestManagement forestManagement) {
        String XID  = forestManagement.getForestManagement().getStringId().toString() ;
        ForestManagementConsumer forest = forestManagement.getForestManagement();
        forest.setId(XID);

        if (forest != null && "create".equals(forestManagement.getEventType())) {
            try {
                forestMangementRepository.save(forest);
                log.info("Forest Management saved: " + forest);
            } catch (Exception e) {
                log.error("Error saving Forest : " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if (forest != null && "update".equals(forestManagement.getEventType())){
            try {
                log.info("Updating project description with ID: " + forest.getId());
                ForestManagementConsumer existingForestP = forestMangementRepository.findById(XID).get();

                existingForestP.setForestVegetation(forest.getForestVegetation());
                existingForestP.setStartForestDegradationLevel(forest.getStartForestDegradationLevel());
                existingForestP.setWithoutForestDegradationLevel(forest.getWithoutForestDegradationLevel());
                existingForestP.setWithForestDegradationLevel(forest.getWithForestDegradationLevel());
                existingForestP.setWithoutFireOccurrence(forest.getWithoutFireOccurrence());
                existingForestP.setWithFireOccurrence(forest.getWithFireOccurrence());
                existingForestP.setWithoutFireImpact(forest.getWithoutFireImpact());
                existingForestP.setWithFireImpact(forest.getWithFireImpact());
                existingForestP.setStartForestedAreaManagement(forest.getStartForestedAreaManagement());
                existingForestP.setWithoutForestedAreaManagement(forest.getWithoutForestedAreaManagement());
                existingForestP.setTypeWithoutForestedAreaManagement(forest.getTypeWithoutForestedAreaManagement());
                existingForestP.setWithForestedAreaManagement(forest.getWithForestedAreaManagement());
                existingForestP.setTypeWithForestedAreaManagement(forest.getTypeWithForestedAreaManagement());
                existingForestP.setWithoutTotEmissionsForest(forest.getWithoutTotEmissionsForest());
                existingForestP.setWithTotEmissionsForest(forest.getWithTotEmissionsForest());
                existingForestP.setBalanceForest(forest.getBalanceForest());
                existingForestP.setWithoutTotForest(forest.getWithoutTotForest());
                existingForestP.setWithTotForest(forest.getWithTotForest());
                existingForestP.setBalanceTotForest(forest.getBalanceTotForest());
                existingForestP.setDegradationLevel(forest.getDegradationLevel());
                existingForestP.setBiomassLost(forest.getBiomassLost());

                forestMangementRepository.save(existingForestP);
                log.info("forest management saved: " + existingForestP);
            } catch (Exception e) {
                log.error("Entity with ID: " + forest.getId() + " not found in the database.");
                log.error("Error updating project description: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if ("delete".equals(forestManagement.getEventType())) {
            // Handle delete event logic
            try {
                log.info("Deleting project description with ID: " + forest.getId());
                // Find the existing project description in the database using its ID
                Optional<ForestManagementConsumer> existingForestOptional = forestMangementRepository.findById(XID);

                // Check if the project description exists
                if (existingForestOptional.isPresent()) {
                    // If it exists, delete the project description from the database
                    forestMangementRepository.deleteById(XID);
                    log.info("Project description deleted with ID: " + XID);
                } else {
                    // If the project description with the specified ID is not found in the database, log an error
                    log.error("Entity with ID: " + XID + " not found in the database.");
                }
            } catch (Exception e) {
                log.error("Error deleting project description: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else {
            log.warn("Invalid event type received: " + forestManagement.getEventType());
            // Handle invalid event types (log a warning, ignore the message, etc.)
        }
    }


}
