package org.ifarmr.service;

import org.ifarmr.entity.Notification;
import org.ifarmr.enums.NotificationType;
import org.ifarmr.payload.request.RecentActivityDto;

import java.util.List;

public interface NotificationService {

    void createNotification(Long userId, String content, NotificationType type);

    List<RecentActivityDto> getRecentActivities(String username);

    List<Notification> getAllNotifications(String username);
}
