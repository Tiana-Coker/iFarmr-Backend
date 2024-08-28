package org.ifarmr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.ifarmr.enums.AnimalType;
import org.ifarmr.enums.Status;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "livestock_tbl")
public class LiveStock extends BaseClass {

    private String animalName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private  AnimalType animalType;

    private String breed;

    private String quantity;

    private String age;

    private Integer wateringFrequency;

    private Integer feedingSchedule;

    private Integer vaccinationSchedule;

    private String healthIssues;

    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    private String photoUpload;

    @CreationTimestamp
    private LocalDateTime dateAdded;

    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("livestock-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

}
