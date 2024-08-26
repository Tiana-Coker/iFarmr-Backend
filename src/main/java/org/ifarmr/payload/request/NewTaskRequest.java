package org.ifarmr.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.ifarmr.enums.TaskCategory;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTaskRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String location;

    @NotNull(message = "Category is required")
    private TaskCategory category;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be today or in the future")
    private LocalDate dueDate;

    private String description;

    public void setCategory(String category) {
        this.category = TaskCategory.valueOf(category.toUpperCase());
    }
}
