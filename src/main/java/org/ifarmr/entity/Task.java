package org.ifarmr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task_tbl")
@Builder
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
