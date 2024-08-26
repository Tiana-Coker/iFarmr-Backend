package org.ifarmr.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {


    //This is an endpoint for testing purpose, You can DELETE THIS AND replace THIS CODE with your endpoint
    //This endpoint should print out the role of the admin
    @GetMapping("/roles")
    public ResponseEntity<?> getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities() != null) {
            return ResponseEntity.ok(authentication.getAuthorities());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No roles found");
    }

}
