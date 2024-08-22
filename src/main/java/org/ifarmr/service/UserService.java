package org.ifarmr.service;

import org.ifarmr.payload.request.UserDetailsDto;

public interface UserService {
    UserDetailsDto editUserDetails(String username, UserDetailsDto userDetailsDto);
}
