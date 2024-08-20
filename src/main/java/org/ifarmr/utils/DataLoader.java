package org.ifarmr.utils;


import org.ifarmr.entity.Role;
import org.ifarmr.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(new Role("USER"));
        }

        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(new Role( "ADMIN"));
        }
    }
}