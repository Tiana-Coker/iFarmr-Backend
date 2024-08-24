package org.ifarmr.service;

import org.ifarmr.entity.User;
import org.ifarmr.payload.request.UserDetailsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {
    UserDetailsDto editUserDetails(String username, UserDetailsDto userDetailsDto, MultipartFile file);
    Optional<User> findByUsername(String username);
}
