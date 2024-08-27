package org.ifarmr.payload.response;

import lombok.*;
import org.ifarmr.enums.TaskCategory;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpcomingTaskResponse {
    private String title;
    private String location;
    private LocalDate dueDate;
    private String description;
    private TaskCategory category;

}
