package com.projet.consumer.models.dto;


import com.projet.consumer.models.ProjectDescriptionConsumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDescription {

    private ProjectDescriptionConsumer projectDescription ;
    private String eventType ;
}

