package org.ifarmr.payload.response;

import lombok.*;
import org.ifarmr.entity.Crop;
import org.ifarmr.enums.CropType;
import org.ifarmr.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropInfo {
    private String cropName;
    private CropType cropType;
    private LocalDate sowDate;
    private LocalDate harvestDate;
    private String numberOfSeedlings;
    private String costOfSeedlings;
    private String wateringFrequency;
    private String fertilizingFrequency;
    private String pestAndDiseases;
    private String photoUpload;
    private String quantity;
    private String location;
    private Status status;
    private String description;

}