package com.projet.consumer.service;

import com.projet.consumer.models.ProjectDescriptionConsumer;
import com.projet.consumer.models.dto.ProjectDescription;
import com.projet.consumer.repository.ProjectDescriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j

public class KafkaConsumerService {

    @Autowired
    ProjectDescriptionRepository projectDescriptionRepository;





    @KafkaListener(topics = {"project-description-topic"}, groupId = "group-id", containerFactory="kafkaListenerContainerFactory")
    public void consumeMessage( ProjectDescription projectDescription) {
       String XID  = projectDescription.getProjectDescription().getStringId().toString() ;
        ProjectDescriptionConsumer project = projectDescription.getProjectDescription();
            project.setId(XID);
        if (project != null && "create".equals(projectDescription.getEventType())) {
            try {
                projectDescriptionRepository.save(project);
                log.info("Project description saved: " + project);
            } catch (Exception e) {
                log.error("Error saving project description: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if (project != null && "update".equals(projectDescription.getEventType())){
            try {
                log.info("Updating project description with ID: " + project.getId());
                ProjectDescriptionConsumer existingProjectDescriptionP = projectDescriptionRepository.findById(XID).get();

                existingProjectDescriptionP.setContinent(project.getContinent());
                existingProjectDescriptionP.setClimate(project.getClimate());
                existingProjectDescriptionP.setCountry(project.getCountry());
                existingProjectDescriptionP.setCh4(project.getCh4());
                existingProjectDescriptionP.setCo2(project.getCo2());
                existingProjectDescriptionP.setProjectCode(project.getProjectCode());
                existingProjectDescriptionP.setProjectCost(project.getProjectCost());
                existingProjectDescriptionP.setProjectName(project.getProjectName());
                existingProjectDescriptionP.setProjectStatus(project.getProjectStatus());
                existingProjectDescriptionP.setMoisture(project.getMoisture());
                existingProjectDescriptionP.setSoCref(project.getSoCref());
                existingProjectDescriptionP.setN2o(project.getN2o());
                existingProjectDescriptionP.setSource(project.getSource());
                existingProjectDescriptionP.setTotalDurationOfAccounting(project.getTotalDurationOfAccounting());
                existingProjectDescriptionP.setCapitalizationPhase(project.getCapitalizationPhase());
                existingProjectDescriptionP.setImplementationPhase(project.getImplementationPhase());
                existingProjectDescriptionP.setSoilType(project.getSoilType());
                existingProjectDescriptionP.setExecutingAgency(project.getExecutingAgency());
                existingProjectDescriptionP.setFundingAgency(project.getFundingAgency());
                existingProjectDescriptionP.setUserName(project.getUserName());

                projectDescriptionRepository.save(existingProjectDescriptionP);
                log.info("Project description saved: " + existingProjectDescriptionP);
            } catch (Exception e) {
                log.error("Entity with ID: " + project.getId() + " not found in the database.");
                log.error("Error updating project description: " + e.getMessage());
                // Handle the exception (e.g., log it, send a notification, etc.)
            }
        } else if ("delete".equals(projectDescription.getEventType())) {
            // Handle delete event logic
            try {
                log.info("Deleting project description with ID: " + project.getId());
                // Find the existing project description in the database using its ID
                Optional<ProjectDescriptionConsumer> existingProjectDescriptionOptional = projectDescriptionRepository.findById(XID);

                // Check if the project description exists
                if (existingProjectDescriptionOptional.isPresent()) {
                    // If it exists, delete the project description from the database
                    projectDescriptionRepository.deleteById(XID);
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
            log.warn("Invalid event type received: " + projectDescription.getEventType());
            // Handle invalid event types (log a warning, ignore the message, etc.)
        }
    }

    }



