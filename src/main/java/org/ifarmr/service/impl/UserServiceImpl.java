package org.ifarmr.service.impl;

import org.ifarmr.entity.User;
import org.ifarmr.exceptions.UserNotFoundException;
import org.ifarmr.payload.request.UserDetailsDto;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("${baseUrl}")
    private String baseUrl;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Edit User Details
    @Override
    public UserDetailsDto editUserDetails(String username, UserDetailsDto userDetailsDto) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("User does not exist!");
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
