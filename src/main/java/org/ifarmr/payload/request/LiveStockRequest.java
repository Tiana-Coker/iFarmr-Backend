package org.ifarmr.payload.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.ifarmr.enums.Status;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveStockRequest {

    @NotNull(message = "Animal name is required")
    @Size(min = 1, max = 30, message = "Animal name must be between 1 and 30 characters")
    @NotNull
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

    @NotNull
    private Status status;

    private String photoUpload;
}
