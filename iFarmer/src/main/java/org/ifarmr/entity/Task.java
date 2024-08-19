package org.ifarmr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseClass{

    private String title;

    private String location;

    private String description;

    private String category;

    private LocalDate dueDate;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("task-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
}
