package com.projet.consumer.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "land_use")
public class OtherLandUseChangesConsumer {
    @Id
    private Double id;
    private String userNotes;
    private Boolean fireUsed;
    private LandUseType initialLandUse3;
    private LandUseType finalLandUse3;
    private int withoutAreaLandUse;
    private Type typeWithoutAreaLandUse;
    private int withAreaLandUse;
    private Type typeWithAreaLandUse;
    private int withoutTotEmissions3;
    private int withTotEmissions3;
    private int balance3;
    private int withoutTotNonForestLandUse;
    private int withTotNonForestLandUse;
    private int balanceTotNonForestLandUse;
}
