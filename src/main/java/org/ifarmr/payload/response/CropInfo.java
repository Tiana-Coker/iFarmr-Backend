package org.ifarmr.payload.response;

import lombok.*;
import org.ifarmr.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropInfo {
    private String cropName;
    private String cropType;
    private LocalDate sowDate;
    private LocalDate harvestDate;
    private String numberOfSeedlings;
    private String costOfSeedlings;
    private String wateringFrequency;
    private String fertilisingFrequency;
    private String pestAndDiseases;
    private String cost;
    private String photoUpload;
    private String quantity;
    private String location;
    private Status status;
    private String description;
}