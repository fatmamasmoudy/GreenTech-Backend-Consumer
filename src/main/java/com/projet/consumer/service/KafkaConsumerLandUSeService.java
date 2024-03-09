package com.projet.consumer.service;

import com.projet.consumer.models.OtherLandUseChangesConsumer;
import com.projet.consumer.models.ProjectDescriptionConsumer;
import com.projet.consumer.models.dto.OtherLandUseChanges;
import com.projet.consumer.repository.OtherLandUseChangesRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaConsumerLandUSeService {

    @Autowired
    OtherLandUseChangesRespository otherLandUseChangesRespository;


    //@KafkaListener(topics = {"Land-use-topic"}, groupId = "group-id")
    @KafkaListener(topics = {"land-use-topic"}, groupId = "group-id1" , containerFactory = "kafkaListenerContainerLandFactory")
    public void consume( OtherLandUseChanges otherLandUseChanges) {
        Double XID  = otherLandUseChanges.getOtherLandUseChanges().getId();
        OtherLandUseChangesConsumer landUse = otherLandUseChanges.getOtherLandUseChanges();

        if (landUse != null && "create".equals(otherLandUseChanges.getEventType())) {
            try {
                otherLandUseChangesRespository.save(landUse);
                log.info("Project description saved: " + landUse);
            } catch (Exception e) {
                log.error("Error saving project description: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if (landUse != null && "update".equals(otherLandUseChanges.getEventType())){
            try {
                log.info("Updating project description with ID: " + landUse.getId());
                OtherLandUseChangesConsumer existingLandUse = otherLandUseChangesRespository.findById(XID).get();

                existingLandUse.setUserNotes(landUse.getUserNotes());
                existingLandUse.setFireUsed(landUse.getFireUsed());
                existingLandUse.setInitialLandUse3(landUse.getInitialLandUse3());
                existingLandUse.setFinalLandUse3(landUse.getFinalLandUse3());
                existingLandUse.setWithoutAreaLandUse(landUse.getWithoutAreaLandUse());
                existingLandUse.setTypeWithAreaLandUse(landUse.getTypeWithAreaLandUse());
                existingLandUse.setTypeWithoutAreaLandUse(landUse.getTypeWithoutAreaLandUse());
                existingLandUse.setWithAreaLandUse(landUse.getWithAreaLandUse());
                existingLandUse.setWithoutTotEmissions3(landUse.getWithoutTotEmissions3());
                existingLandUse.setWithTotEmissions3(landUse.getWithTotEmissions3());
                existingLandUse.setBalance3(landUse.getBalance3());
                existingLandUse.setWithTotNonForestLandUse(landUse.getWithTotNonForestLandUse());
                existingLandUse.setWithoutTotNonForestLandUse(landUse.getWithoutTotNonForestLandUse());
                existingLandUse.setBalanceTotNonForestLandUse(landUse.getBalanceTotNonForestLandUse());

                otherLandUseChangesRespository.save(existingLandUse);
                log.info("Project description saved: " + existingLandUse);
            } catch (Exception e) {
                log.error("Entity with ID: " + landUse.getId() + " not found in the database.");
                log.error("Error updating project description: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if ("delete".equals(otherLandUseChanges.getEventType())) {
            // Handle delete event logic
            try {
                log.info("Deleting project description with ID: " + landUse.getId());
                // Find the existing project description in the database using its ID
                Optional<OtherLandUseChangesConsumer> existingLandUseOptional = otherLandUseChangesRespository.findById(XID);

                // Check if the project description exists
                if (existingLandUseOptional.isPresent()) {
                    // If it exists, delete the project description from the database
                    otherLandUseChangesRespository.deleteById(XID);
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
            log.warn("Invalid event type received: " + otherLandUseChanges.getEventType());
            // Handle invalid event types (log a warning, ignore the message, etc.)
        }
    }

}



