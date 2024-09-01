package org.ifarmr.controller;

import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.Notification;
import org.ifarmr.payload.request.RecentActivityDto;
import org.ifarmr.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/recent-activities")
    public ResponseEntity<List<RecentActivityDto>> getRecentActivities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<RecentActivityDto> activities = notificationService.getRecentActivities(username);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Notification> notifications = notificationService.getAllNotifications(username);
        return ResponseEntity.ok(notifications);
    }
}
