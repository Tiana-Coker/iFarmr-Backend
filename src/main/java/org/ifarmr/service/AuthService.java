package org.ifarmr.service;

import jakarta.mail.MessagingException;
import org.ifarmr.payload.request.LoginRequest;
import org.ifarmr.payload.request.UserRegisterRequest;
import org.ifarmr.payload.response.LoginResponse;

public interface AuthService {

    String registerUser(UserRegisterRequest userRegisterRequest) throws MessagingException;

    LoginResponse loginUser(LoginRequest loginRequest);
}
