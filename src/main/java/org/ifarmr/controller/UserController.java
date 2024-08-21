package org.ifarmr.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ifarmr.payload.response.CloudinaryResponse;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final FileUploadService fileUploadService;

    @PutMapping("/upload/profilephoto")
    public ResponseEntity<CloudinaryResponse> profilePicture (@RequestParam("file") MultipartFile file){
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        return ResponseEntity.ok(fileUploadService.uploadProfilePicture (username, file));
    }

}
