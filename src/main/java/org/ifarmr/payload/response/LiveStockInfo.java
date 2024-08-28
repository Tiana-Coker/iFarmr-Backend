package org.ifarmr.payload.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.ifarmr.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveStockInfo {
    private String animalName;
    private String animalType;
    private String breed;
    private String quantity;
    private String age;
    private String wateringFrequency;
    private String feedingSchedule;
    private String vaccinationSchedule;
    private String healthIssues;
    private String description;
    private Status status;
    private String photoUpload;
}
