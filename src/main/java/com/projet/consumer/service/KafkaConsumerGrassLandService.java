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
                log.info("Forest Management saved: " + grass.getId());
            } catch (Exception e) {
                log.error("Error saving Forest : " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if (grass != null && "update".equals(grassLand.getEventType())){
            try {
                log.info("Updating grassLand with ID: " + grass.getId());
                GrasslandConsumer existingGrassP = grassLandRepository.findById(XID).get();

                existingGrassP.setUserNotesGrassland(grass.getUserNotesGrassland());
                existingGrassP.setWithoutFireMang2(grass.getWithoutFireMang2());
                existingGrassP.setWithFireMang2(grass.getWithFireMang2());
                existingGrassP.setStartYield2(grass.getStartYield2());
                existingGrassP.setWithoutYield2(grass.getWithoutYield2());
                existingGrassP.setStartAreaGrassland2(grass.getStartAreaGrassland2());
                existingGrassP.setWithYield2(grass.getWithYield2());
                existingGrassP.setWithoutAreaGrassland2(grass.getWithoutAreaGrassland2());
                existingGrassP.setTypeWithAreaGrassland2(grass.getTypeWithAreaGrassland2());
                existingGrassP.setWithoutTotEmissionsGrassland2(grass.getWithoutTotEmissionsGrassland2());
                existingGrassP.setWithAreaGrassland2(grass.getWithAreaGrassland2());
                existingGrassP.setBalanceGrassland2(grass.getBalanceGrassland2());
                existingGrassP.setWithoutTotGrasslandSys(grass.getWithoutTotGrasslandSys());
                existingGrassP.setWithTotGrasslandSys(grass.getWithTotGrasslandSys());
                existingGrassP.setBalanceTotGrasslandSys(grass.getBalanceTotGrasslandSys());


                grassLandRepository.save(existingGrassP);
                log.info("forest management saved: " + existingGrassP);
            } catch (Exception e) {
                log.error("Entity with ID: " + grass.getId() + " not found in the database.");
                log.error("Error updating project description: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if ("delete".equals(grassLand.getEventType())) {
            // Handle delete event logic
            try {
                log.info("Deleting project description with ID: " + grass.getId());
                // Find the existing project description in the database using its ID
                Optional<GrasslandConsumer> existingGrassLandOptional = grassLandRepository.findById(XID);

                // Check if the project description exists
                if (existingGrassLandOptional.isPresent()) {
                    // If it exists, delete the project description from the database
                    grassLandRepository.deleteById(XID);
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
            log.warn("Invalid event type received: " + grassLand.getEventType());
            // Handle invalid event types (log a warning, ignore the message, etc.)
        }
    }


}
