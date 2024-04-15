package com.projet.consumer.service;


import com.projet.consumer.models.ForestManagementConsumer;
import com.projet.consumer.models.GrasslandConsumer;
import com.projet.consumer.models.dto.ForestManagement;
import com.projet.consumer.models.dto.GrassLand;
import com.projet.consumer.repository.ForestMangementRepository;
import com.projet.consumer.repository.GrassLandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j

public class KafkaConsumerGrassLandService {

    @Autowired
    GrassLandRepository grassLandRepository;


    //@KafkaListener(topics = {"Land-use-topic"}, groupId = "group-id")
    @KafkaListener(topics = {"grass-land-topic"}, groupId = "group-id2" , containerFactory = "kafkaListenerContainerGrassFactory")
    public void consume( GrassLand grassLand) {
        String XID  = grassLand.getGrassland().getStringId().toString() ;
        GrasslandConsumer grass = grassLand.getGrassland();
        grass.setId(XID);

        if (grass != null && "create".equals(grassLand.getEventType())) {
            try {
                grassLandRepository.save(grass);
                log.info("Grassland saved: " + grass.getId());
            } catch (Exception e) {
                log.error("Error saving Forest : " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if (grass != null && "update".equals(grassLand.getEventType())){
            try {
                log.info("Updating grassLand with ID: " + grass.getId());
                GrasslandConsumer existingGrassP = grassLandRepository.findById(XID).get();

                existingGrassP.setDescriptionGrassland(grass.getDescriptionGrassland());
                existingGrassP.setStartGrasslandManagement(grass.getStartGrasslandManagement());
                existingGrassP.setWithoutGrasslandManagement(grass.getWithoutGrasslandManagement());
                existingGrassP.setWithGrasslandManagement(grass.getWithGrasslandManagement());
                existingGrassP.setWithFireManagement(grass.getWithFireManagement());
                existingGrassP.setStartYield(grass.getStartYield());
                existingGrassP.setWithoutYield(grass.getWithoutYield());
                existingGrassP.setWithYield(grass.getWithYield());
                existingGrassP.setStartAreaGrassland(grass.getStartAreaGrassland());
                existingGrassP.setWithoutAreaGrassland(grass.getWithoutAreaGrassland());
                existingGrassP.setWithAreaGrassland(grass.getWithAreaGrassland());
                existingGrassP.setWithoutTotEmissionsGrassland(grass.getWithoutTotEmissionsGrassland());
                existingGrassP.setWithTotEmissionsGrassland(grass.getWithTotEmissionsGrassland());
                existingGrassP.setBalanceGrassland(grass.getBalanceGrassland());
                existingGrassP.setTotGrasslandSystemWithout(grass.getTotGrasslandSystemWithout());
                existingGrassP.setTotGrasslandSystemWith(grass.getTotGrasslandSystemWith());
                existingGrassP.setTotGrasslandSystemBalance(grass.getTotGrasslandSystemBalance());
                grassLandRepository.save(existingGrassP);
                log.info("Grassland saved: " + existingGrassP);
            } catch (Exception e) {
                log.error("Entity with ID: " + grass.getId() + " not found in the database.");
                log.error("Error updating Grassland: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if ("delete".equals(grassLand.getEventType())) {
            // Handle delete event logic
            try {
                log.info("Deleting Grassland with ID: " + grass.getId());
                // Find the existing project description in the database using its ID
                Optional<GrasslandConsumer> existingGrassLandOptional = grassLandRepository.findById(XID);

                // Check if the project description exists
                if (existingGrassLandOptional.isPresent()) {
                    // If it exists, delete the project description from the database
                    grassLandRepository.deleteById(XID);
                    log.info("Grassland deleted with ID: " + XID);
                } else {
                    // If the project description with the specified ID is not found in the database, log an error
                    log.error("Entity with ID: " + XID + " not found in the database.");
                }
            } catch (Exception e) {
                log.error("Error deleting Grassland: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else {
            log.warn("Invalid event type received: " + grassLand.getEventType());
            // Handle invalid event types (log a warning, ignore the message, etc.)
        }
    }


}
