package org.ifarmr.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ifarmr.enums.Gender;
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
    public ResponseEntity<UserDetailsDto> editUserDetails(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "gender", required = false) Gender gender) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        UserDetailsDto userDetailsDto = UserDetailsDto.builder()
                .fullName(fullName)
                .username(username)
                .gender(gender)
                .build();

        return ResponseEntity.ok(userService.editUserDetails(currentUsername, userDetailsDto, file));
    }

}
