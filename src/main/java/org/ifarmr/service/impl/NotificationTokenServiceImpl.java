package org.ifarmr.service.impl;

import org.ifarmr.entity.NotificationToken;
import org.ifarmr.repository.NotificationTokenRepository;
import org.ifarmr.service.NotificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTokenServiceImpl implements NotificationTokenService {
    @Autowired
    private NotificationTokenRepository tokenRepository;


    @Override
    // Method to get all tokens by user ID
    public List<NotificationToken> getTokensByUserId(Long userId) {
        return tokenRepository.findByUserId(userId);
    }

    public boolean tokenExists(String token) {
        return tokenRepository.existsByToken(token);
    }

    @Override
    public NotificationToken saveToken(Long userId, String token) {
        NotificationToken notificationToken = new NotificationToken();
        notificationToken.setUserId(userId);
        notificationToken.setToken(token);
        return tokenRepository.save(notificationToken);
    }

    @Override
    public void deleteToken(Long userId, String token) {
        tokenRepository.deleteByUserIdAndToken(userId, token);
    }

}
