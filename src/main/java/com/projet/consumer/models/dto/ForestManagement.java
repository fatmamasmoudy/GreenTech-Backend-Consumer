package com.projet.consumer.models.dto;
import com.projet.consumer.models.ForestManagementConsumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ForestManagement {
    private ForestManagementConsumer forestManagement ;
    private String eventType ;

}
