package org.ifarmr.service;

import jakarta.mail.MessagingException;
import org.ifarmr.payload.request.UserRegisterRequest;

public interface AuthService {

    String registerUser(UserRegisterRequest userRegisterRequest) throws MessagingException;
}
