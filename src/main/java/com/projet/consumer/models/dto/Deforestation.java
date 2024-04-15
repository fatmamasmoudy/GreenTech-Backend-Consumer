package com.projet.consumer.models.dto;

import com.projet.consumer.models.DeforestationConsumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Deforestation {
    private DeforestationConsumer deforestation;
    private String eventType ;
}
