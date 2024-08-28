package org.ifarmr.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.ifarmr.enums.Status;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropRequest {

    @NotNull(message = "Crop name is required")
    @Size(min = 1, max = 30, message = "Crop name must be between 1 and 30 characters")
    @NotNull
    private String cropName;

    @NotNull(message = "Crop type is required")
    private String cropType;

    @NotNull(message = "Harvest Date is required")
    private LocalDateTime harvestDate;

    @NotNull(message = "Number of Seeds are required")
    private String numberOfSeedlings;

    @NotNull(message = "Cost of Seed is required")
    private String costOfSeedlings;

    @NotNull(message = "This is required")
    private String wateringFrequency;

    @NotNull(message = "This is required")
    private String fertilisingFrequency;

    @NotNull(message = "This is required")
    private String pestAndDiseases;

    @NotNull(message = "This is required")
    private String photoUpload;

    @NotNull
    private Status status;

    @NotNull
    private String description;

    // Extra fields for the dashboard
    private String quantity;
    private String location;

    @NotNull(message = "Sow Date is required")
    private LocalDateTime sowDate;

}
