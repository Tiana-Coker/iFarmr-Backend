package org.ifarmr.service.impl;

import org.ifarmr.entity.NotificationToken;
import org.ifarmr.payload.request.NotificationRequest;
import org.ifarmr.repository.NotificationTokenRepository;
import org.ifarmr.service.NotificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationService {

    @Autowired
    private NotificationTokenRepository tokenRepository;

    @Autowired
    private FCMService fcmService;

    @Autowired
    NotificationTokenService notificationTokenService;

    public void sendNotificationToAll(NotificationRequest request) throws ExecutionException, InterruptedException {
        List<NotificationToken> tokens = tokenRepository.findAll();
        for (NotificationToken token : tokens) {
            request.setToken(token.getToken());
            fcmService.sendMessageToToken(request);
        }
    }



public void sendNotificationToUser(Long userId, NotificationRequest request) throws ExecutionException, InterruptedException {
    System.out.println("ADMIN ID FROM SERVICE METHOD" + userId);
    // Retrieve tokens for the specific user using the service method
    List<NotificationToken> tokens = notificationTokenService.getTokensByUserId(userId);

//    NotificationToken token = tokens.get(0);
//    System.out.println(" FIRST TOKEN " + tokens.get(0).toString());

    // Check if tokens are found
    if (tokens.isEmpty()) {
        throw new RuntimeException("No tokens found for user with ID: " + userId);
    }

    // Send notification to each token
    for (NotificationToken token : tokens) {
        request.setToken(token.getToken());
        fcmService.sendMessageToToken(request);
//        System.out.println("notification sent from inside service method");
    }
}


}
