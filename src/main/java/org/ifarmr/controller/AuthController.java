package org.ifarmr.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ifarmr.payload.request.UserRegisterRequest;
import org.ifarmr.service.AuthService;
import org.ifarmr.service.TokenValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenValidationService tokenValidationService;
    private final AuthService authService;

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmEmail(@RequestParam("token") String token){

        String result = tokenValidationService.validateToken(token);
        if ("Email confirmed successfully".equals(result)) {
            return ResponseEntity.ok(Collections.singletonMap("message", result));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", result));
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {

        try {
            String registerUser = authService.registerUser(userRegisterRequest);
            if (!registerUser.equals("Invalid Email domain")) {
                return ResponseEntity.ok("User registered successfully. Please check your email to confirm your account");
            } else {
                return ResponseEntity.badRequest().body("Invalid Email!!!");
            }

        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
