package org.ifarmr.service.impl;

import org.ifarmr.entity.User;
import org.ifarmr.payload.request.UserDetailsDto;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.UserService;
import org.springframework.beans.factory.annotation.Value;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("${baseUrl}")
    private String baseUrl;

    // Edit User Details
    @Override
    public UserDetailsDto editUserDetails(String username, UserDetailsDto userDetailsDto) {

        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null){
            throw new UserNotFoundException("user does not exist!");
        }

        user.setFullName(userDetailsDto.getFullName());
        user.setEmail(userDetailsDto.getEmail());
        user.setUsername(userDetailsDto.getUsername());

        User newUser = userRepository.save(user);

        return UserDetailsDto.builder()
                .fullName(newUser.getFullName())
                .email(newUser.getEmail())
                .username(newUser.getUsername())
                .build();
    }
}