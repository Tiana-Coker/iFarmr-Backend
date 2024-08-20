package org.ifarmr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "crop_tbl")
public class Crop extends BaseClass {

    private String name;

    private String cropType;

    private LocalDateTime sowDate;

    private LocalDateTime harvestDate;

    private String numberOfSeeds;

    private String costOfSeed;

    private String wateringFrequency;

    private String fertilisingFrequency;

    private String pestAndDiseases;

    private String cost;

    private String photoUpload;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("crop-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
}
