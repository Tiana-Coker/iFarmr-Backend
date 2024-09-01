package org.ifarmr.payload.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RecentActivityDto {
    private String icon;
    private String title;
    private String description;
    private LocalDateTime timeAgo;
    private LocalDateTime date;
}
