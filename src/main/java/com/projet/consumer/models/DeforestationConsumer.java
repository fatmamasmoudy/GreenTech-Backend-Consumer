package com.projet.consumer.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "deforestation")
public class DeforestationConsumer {
    @Id
    private String id;
    private String StringId ;
    private String vegetationUsed;
    private float hwps;
    private boolean fireUsed;
    private String landUseType;
    private String agroforestrySystem;
    private double startForestedArea1;
    private double withoutForestedArea1;
    private String typeWithout1;
    private double withForestedArea1;
    private String typeWith1;
    private double withoutDeforestedArea1;
    private double withDeforestedArea1;
    private double withoutTotEmissions1;
    private double withTotEmissions1;
    private double balance1;
    private double withoutTotDeforestation;
    private double withTotDeforestation;
    private double balanceTotDeforestation;

}
