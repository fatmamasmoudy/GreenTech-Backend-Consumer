package com.projet.consumer.models.dto;

import com.projet.consumer.models.GrasslandConsumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GrassLand {
    private GrasslandConsumer grassland ;
    private String eventType ;


}
