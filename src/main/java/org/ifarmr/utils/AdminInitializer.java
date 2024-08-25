package org.ifarmr.utils;

import org.ifarmr.entity.Role;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.repository.RoleRepository;
import org.ifarmr.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    // Constructor injection
    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            String adminUsername = "admin";
            String adminPassword = "admin1";

            // Find admin role in database
            Optional<Role> adminRole = roleRepository.findByName("ADMIN");
            if (adminRole.isEmpty()) {
                throw new NotFoundException("Default role ADMIN not found in the database.");
            }
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole.get());

            if (userRepository.findByUsername(adminUsername).isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername(adminUsername);
                adminUser.setPassword(passwordEncoder.encode(adminPassword));
                adminUser.setRoles(roles);
                adminUser.setEnabled(true);

                userRepository.save(adminUser);

                System.out.println("Admin user seeded into the database.");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}