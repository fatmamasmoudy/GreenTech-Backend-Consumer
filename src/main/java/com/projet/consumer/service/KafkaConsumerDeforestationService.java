package com.projet.consumer.service;


import com.projet.consumer.models.DeforestationConsumer;
import com.projet.consumer.models.ProjectDescriptionConsumer;
import com.projet.consumer.models.dto.Deforestation;
import com.projet.consumer.repository.DeforestationRepository;
import com.projet.consumer.repository.ProjectDescriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaConsumerDeforestationService {

    @Autowired
    ProjectDescriptionRepository projectDescriptionRepository;

    @Autowired
    DeforestationRepository deforestationRepository;



    @KafkaListener(topics = {"land-use-topic"}, groupId = "group-id", containerFactory="kafkaListenerContainerFactory")
    public void consumeMessage( Deforestation deforestation) {
        String XID  = deforestation.getDeforestation().getStringId().toString() ;
        DeforestationConsumer defo = deforestation.getDeforestation();
        defo.setId(XID);
        if (defo != null && "create".equals(deforestation.getEventType())) {
            try {
                deforestationRepository.save(defo);
                log.info("Deforestation saved: " + defo);
            } catch (Exception e) {
                log.error("Error saving deforestation: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if (defo != null && "update".equals(deforestation.getEventType())){
            try {
                log.info("Updating deforestation with ID: " + defo.getId());
                DeforestationConsumer existingDeforestationP = deforestationRepository.findById(XID).get();

                existingDeforestationP.setVegetationUsed(defo.getVegetationUsed());
                existingDeforestationP.setHwps(defo.getHwps());
                existingDeforestationP.setFireUsed(defo.isFireUsed());
                existingDeforestationP.setLandUseType(defo.getLandUseType());
                existingDeforestationP.setAgroforestrySystem(defo.getAgroforestrySystem());
                existingDeforestationP.setStartForestedArea1(defo.getStartForestedArea1());
                existingDeforestationP.setWithoutForestedArea1(defo.getWithoutForestedArea1());
                existingDeforestationP.setTypeWithout1(defo.getTypeWithout1());
                existingDeforestationP.setWithForestedArea1(defo.getWithForestedArea1());
                existingDeforestationP.setTypeWith1(defo.getTypeWith1());
                existingDeforestationP.setWithoutDeforestedArea1(defo.getWithoutDeforestedArea1());
                existingDeforestationP.setWithDeforestedArea1(defo.getWithDeforestedArea1());
                existingDeforestationP.setWithoutTotDeforestation(defo.getWithoutTotDeforestation());
                existingDeforestationP.setWithTotDeforestation(defo.getWithTotDeforestation());
                existingDeforestationP.setBalance1(defo.getBalance1());
                existingDeforestationP.setWithoutTotDeforestation(defo.getWithoutTotDeforestation());
                existingDeforestationP.setWithTotDeforestation(defo.getWithTotDeforestation());
                existingDeforestationP.setBalanceTotDeforestation(defo.getBalanceTotDeforestation());



                deforestationRepository.save(existingDeforestationP);
                log.info("Deforestation saved: " + existingDeforestationP);
            } catch (Exception e) {
                log.error("Entity with ID: " + defo.getId() + " not found in the database.");
                log.error("Error updating Deforestation: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if ("delete".equals(deforestation.getEventType())) {
            // Handle delete event logic
            try {
                log.info("Deleting Deforestation with ID: " + defo.getId());
                // Find the existing project description in the database using its ID
                Optional<DeforestationConsumer> existingDeforestationOptional = deforestationRepository.findById(XID);

                // Check if the project description exists
                if (existingDeforestationOptional.isPresent()) {
                    // If it exists, delete the project description from the database
                    deforestationRepository.deleteById(XID);
                    log.info("Deforestation deleted with ID: " + XID);
                } else {
                    // If the project description with the specified ID is not found in the database, log an error
                    log.error("Entity with ID: " + XID + " not found in the database.");
                }
            } catch (Exception e) {
                log.error("Error deleting deforestation: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else {
            log.warn("Invalid event type received: " + deforestation.getEventType());
            // Handle invalid event types (log a warning, ignore the message, etc.)
        }
    }
}
