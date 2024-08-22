package org.ifarmr.service.impl;

import org.ifarmr.entity.User;
import org.ifarmr.exceptions.UserNotFoundException;
import org.ifarmr.payload.request.UserDetailsDto;
import org.ifarmr.payload.response.CloudinaryResponse;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.FileUploadService;
import org.ifarmr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    // Define regex patterns
    private static final Pattern FULL_NAME_PATTERN = Pattern.compile("^[a-zA-Z ]+$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$");

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FileUploadService fileUploadService) {
        this.userRepository = userRepository;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public UserDetailsDto editUserDetails(String username, UserDetailsDto userDetailsDto, MultipartFile file) {

        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("User does not exist!");
        }

        // Validate fullName
        if (userDetailsDto.getFullName() != null && !userDetailsDto.getFullName().trim().isEmpty()) {
            if (!FULL_NAME_PATTERN.matcher(userDetailsDto.getFullName()).matches()) {
                throw new IllegalArgumentException("Full Name must contain only alphabets and spaces");
            }
            user.setFullName(userDetailsDto.getFullName());
        }

        // Validate username
        if (userDetailsDto.getUsername() != null && !userDetailsDto.getUsername().trim().isEmpty()) {
            if (!USERNAME_PATTERN.matcher(userDetailsDto.getUsername()).matches()) {
                throw new IllegalArgumentException("Username must contain at least one alphabet and one number, and only alphabets and numbers are allowed");
            }
            user.setUsername(userDetailsDto.getUsername());
        }

        if (userDetailsDto.getGender() != null) {
            user.setGender(userDetailsDto.getGender());
        }

        // Upload the profile picture and get the URL
        CloudinaryResponse response = fileUploadService.uploadProfilePicture(username, file);
        String profilePictureUrl = response.getFileUrl();

        if (profilePictureUrl != null && !profilePictureUrl.trim().isEmpty()) {
            user.setDisplayPhoto(profilePictureUrl);
        }

        User updatedUser = userRepository.save(user);

        return UserDetailsDto.builder()
                .fullName(updatedUser.getFullName())
                .username(updatedUser.getUsername())
                .gender(updatedUser.getGender())
                .profilePictureUrl(updatedUser.getDisplayPhoto())
                .build();
    }
}
