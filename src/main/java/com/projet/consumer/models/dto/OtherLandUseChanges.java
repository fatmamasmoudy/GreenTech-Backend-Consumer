package com.projet.consumer.models.dto;

import com.projet.consumer.models.OtherLandUseChangesConsumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherLandUseChanges {

    private OtherLandUseChangesConsumer otherLandUseChanges ;
    private String eventType ;
}
