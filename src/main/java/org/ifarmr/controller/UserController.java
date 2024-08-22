package org.ifarmr.controller;

import lombok.RequiredArgsConstructor;
import org.ifarmr.payload.request.UserDetailsDto;
import org.ifarmr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Edit User Details
    @PutMapping("/edit-user-details")
    public ResponseEntity<UserDetailsDto> editUserDetails(@RequestBody UserDetailsDto adminUserDetailsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return ResponseEntity.ok(userService.editUserDetails(currentUsername, adminUserDetailsDto));
    }
}
