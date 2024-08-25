package org.ifarmr.payload.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskInfo {
    private String title;

    private String location;

    private String category;

    private LocalDate dueDate;

    private String description;
}
