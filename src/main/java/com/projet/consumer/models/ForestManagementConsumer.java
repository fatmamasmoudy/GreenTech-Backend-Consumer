package com.projet.consumer.models;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Forest_management")
public class ForestManagementConsumer {

    @Id
    private String id;
    private String StringId ;
    private ForestVegetation forestVegetation;
    private ForestDegradationLevel startForestDegradationLevel;
    private ForestDegradationLevel withoutForestDegradationLevel;
    private ForestDegradationLevel withForestDegradationLevel;
    private Boolean withoutFireOccurrence;
    private Boolean withFireOccurrence;
    static final int FirePeriodicity = 1;

    private float withoutFireImpact;
    private float withFireImpact;
    private int startForestedAreaManagement;
    private int withoutForestedAreaManagement;
    private Type typeWithoutForestedAreaManagement;
    private int withForestedAreaManagement;
    private Type typeWithForestedAreaManagement;
    private int withoutTotEmissionsForest;
    private int withTotEmissionsForest;
    private int balanceForest;
    private int withoutTotForest;
    private int withTotForest;
    private int balanceTotForest;
    private ForestDegradationLevel degradationLevel;
    private int biomassLost;




}
