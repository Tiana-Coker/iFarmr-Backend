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

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

        // Upload the profile picture and get the URL
        CloudinaryResponse response = fileUploadService.uploadProfilePicture(username, file);
        String profilePictureUrl = response.getFileUrl();

        // Update user details only if the new values are not null or empty
        if (userDetailsDto.getFullName() != null && !userDetailsDto.getFullName().trim().isEmpty()) {
            user.setFullName(userDetailsDto.getFullName());
        }

        if (userDetailsDto.getUsername() != null && !userDetailsDto.getUsername().trim().isEmpty()) {
            user.setUsername(userDetailsDto.getUsername());
        }

        if (userDetailsDto.getGender() != null) {
            user.setGender(userDetailsDto.getGender());
        }

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
