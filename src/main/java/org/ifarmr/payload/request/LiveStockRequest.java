package org.ifarmr.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.ifarmr.enums.AnimalType;
import org.ifarmr.enums.Status;
import org.springframework.web.multipart.MultipartFile;

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

    private AnimalType animalType;

    private String breed;

    private String quantity;

    private String age;

    @NotNull(message = "This is required")
    private MultipartFile photoUpload;

    private Integer wateringFrequency;

    private Integer feedingSchedule;

    private Integer vaccinationSchedule;

    private String healthIssues;

    private String description;

    @NotNull
    private Status status;

}
