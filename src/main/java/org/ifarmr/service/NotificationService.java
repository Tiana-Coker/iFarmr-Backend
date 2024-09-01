package org.ifarmr.service;

import org.ifarmr.entity.Notification;
import org.ifarmr.enums.NotificationType;

import java.util.List;

public interface NotificationService {

    void createNotification(Long userId, String content, NotificationType type);

    List<Notification> getRecentActivities(String username);

    List<Notification> getAllNotifications(String username);
}
