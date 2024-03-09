package com.projet.consumer.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "project_descriptions")
public class ProjectDescriptionConsumer {
    @Id
    private String id;
    private String StringId ;
    private String userName;
    private LocalDate date;
    private String projectName;
    private int projectCode;
    private float projectCost;
    private String fundingAgency;
    private String executingAgency;
    private ProjectStatus projectStatus;
    private Continent continent;
    private Country country;
    private Climate climate;
    private Moisture moisture;
    private SoilType soilType;
    private int implementationPhase;
    private int capitalizationPhase;
    private int totalDurationOfAccounting;
    private Source source;
    private int co2;
    private int ch4;
    private int n2o;
    private float soCref;
    private String operationType; // Add this field for the operation type (CREATE, UPDATE, DELETE)

}
