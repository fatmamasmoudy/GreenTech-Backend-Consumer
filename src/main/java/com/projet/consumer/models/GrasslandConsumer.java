package com.projet.consumer.models;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "grass_land")
public class GrasslandConsumer {

    @Id
    private String id;
    private String StringId ;
    private String descriptionGrassland;
    private String startGrasslandManagement;
    private String withoutGrasslandManagement;
    private String withGrasslandManagement;
    private Boolean withoutFireManagement;
    static final int year = 5;
    private Boolean withFireManagement;
    static final int year2 = 5;
    private Double startYield;
    private Double withoutYield;
    private Double withYield;
    private Double startAreaGrassland;
    private Double withoutAreaGrassland;
    private Double withAreaGrassland;
    private Double withoutTotEmissionsGrassland;
    private Double withTotEmissionsGrassland;
    private Double balanceGrassland;
    private Double totGrasslandSystemWithout;
    private Double totGrasslandSystemWith;
    private Double totGrasslandSystemBalance;

}
