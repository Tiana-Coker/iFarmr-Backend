package org.ifarmr.service.impl;

import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.Notification;
import org.ifarmr.entity.User;
import org.ifarmr.enums.NotificationType;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.repository.NotificationRepository;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public void createNotification(Long userId, String content, NotificationType type) {
        User user = findUserById(userId);

        Notification notification = Notification.builder()
                .content(content)
                .type(type)
                .user(user)
                .build();

        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getRecentActivities(String username) {
        User user = findUserByUsername(username);
        return notificationRepository.findByUserIdOrderByDateCreatedDesc(user.getId());
    }

    @Override
    public List<Notification> getAllNotifications(String username) {
        User user = findUserByUsername(username);
        return notificationRepository.findByUserIdOrderByDateCreatedDesc(user.getId());
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
