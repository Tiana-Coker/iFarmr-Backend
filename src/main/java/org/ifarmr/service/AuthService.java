package org.ifarmr.service;

import jakarta.mail.MessagingException;
import org.ifarmr.payload.request.ConfirmPasswordRequest;
import org.ifarmr.payload.request.LoginRequest;
import org.ifarmr.payload.request.PasswordResetRequest;
import org.ifarmr.payload.request.UserRegisterRequest;
import org.ifarmr.payload.response.LoginResponse;

public interface AuthService {

    String registerUser(UserRegisterRequest userRegisterRequest) throws MessagingException;

    LoginResponse loginUser(LoginRequest loginRequest);

    //important
    String forgotPasswordRequest(PasswordResetRequest passwordResetRequest) throws MessagingException;



    String confirmResetPassword(String token, ConfirmPasswordRequest confirmPasswordRequest) throws MessagingException;

    void validateToken(String token);
}
