package org.ifarmr.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ifarmr.payload.request.UserDetailsDto;
import org.ifarmr.payload.response.CloudinaryResponse;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.FileUploadService;
import org.ifarmr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final FileUploadService fileUploadService;

    @PutMapping("/upload/profilephoto")
    public ResponseEntity<CloudinaryResponse> profilePicture (@RequestParam("file") MultipartFile file){
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        return ResponseEntity.ok(fileUploadService.uploadProfilePicture (username, file));
    }

    // Edit User Details
    @PutMapping("/edit-user-details")
    public ResponseEntity<UserDetailsDto> editUserDetails(@RequestBody UserDetailsDto adminUserDetailsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return ResponseEntity.ok(userService.editUserDetails(currentUsername, adminUserDetailsDto));
    }

}
