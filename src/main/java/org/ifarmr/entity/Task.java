package org.ifarmr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.ifarmr.enums.TaskCategory;

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

    @Enumerated(EnumType.STRING)
    private TaskCategory category;

    private LocalDate dueDate;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("task-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
}
