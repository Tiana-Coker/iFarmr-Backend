package org.ifarmr.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.Inventory;
import org.ifarmr.entity.User;
import org.ifarmr.enums.Gender;
import org.ifarmr.exceptions.AccessDeniedException;
import org.ifarmr.exceptions.InventoryEmptyException;
import org.ifarmr.exceptions.UserNotFoundException;
import org.ifarmr.payload.request.UserDetailsDto;
import org.ifarmr.payload.response.CloudinaryResponse;
import org.ifarmr.service.FileUploadService;
import org.ifarmr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final FileUploadService fileUploadService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PutMapping(value="/upload/profilephoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CloudinaryResponse> profilePicture (@RequestParam("file") MultipartFile file){
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        return ResponseEntity.ok(fileUploadService.uploadProfilePicture(username, file));
    }
    @PutMapping(value = "/edit-user-details", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDetailsDto> editUserDetails(
           @Valid @RequestParam(value = "file", required = false) MultipartFile file,
           @Valid  @RequestParam(value = "fullName", required = false) String fullName,
           @Valid @RequestParam(value = "username", required = false) String username,
           @Valid @RequestParam(value = "gender", required = false) Gender gender,
           @Valid  @RequestParam(value = "password", required = false) String password, // Use 'password' instead of 'newPassword'
           @Valid   @RequestParam(value = "bio", required = false) String bio
    ) {
        // Get the currently authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Create the UserDetailsDto from the request parameters, including the password
        UserDetailsDto userDetailsDto = UserDetailsDto.builder()
                .fullName(fullName)
                .username(username)
                .gender(gender)
                .bio(bio)
                .password(password) // Set the password
                .build();

        // Call the service to update user details
        UserDetailsDto updatedUserDetails = userService.editUserDetails(currentUsername, userDetailsDto, file);

        return ResponseEntity.ok(updatedUserDetails);
    }



    @GetMapping("/inventory")
    public ResponseEntity<?> getAllInventoryByUser(@AuthenticationPrincipal User authenticatedUser) {
        try {
            List<Inventory> inventoryList = userService.getInventoryForUser(authenticatedUser);
            return ResponseEntity.ok(inventoryList);
        } catch (UserNotFoundException | AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (InventoryEmptyException e) {
            // Return a 200 OK status with the message when the inventory is empty
            return ResponseEntity.ok(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @GetMapping("/get-user-details")
    public ResponseEntity<UserDetailsDto> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        UserDetailsDto userDetailsDto = userService.getUserDetails(currentUsername);

        return ResponseEntity.ok(userDetailsDto);
    }

}
