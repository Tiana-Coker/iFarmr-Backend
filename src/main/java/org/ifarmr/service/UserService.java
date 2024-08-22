package org.ifarmr.service;

import org.ifarmr.payload.request.UserDetailsDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserDetailsDto editUserDetails(String username, UserDetailsDto userDetailsDto, MultipartFile file);
}
