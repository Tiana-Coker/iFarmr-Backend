package org.ifarmr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.ifarmr.enums.Status;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "livestock_tbl")
public class LiveStock extends BaseClass {

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

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    private String photoUpload;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("livestock-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;


}
