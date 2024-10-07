package org.ifarmr.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ifarmr.entity.Tip;
import org.ifarmr.repository.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  // Add this annotation to ensure it runs at startup
public class TipJsonSeeder implements CommandLineRunner {

    @Autowired
    private TipRepository tipRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if tips already exist to avoid duplication
        if (tipRepository.count() == 0) {
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse JSON file into a list of Tip objects
            List<Tip> tips = objectMapper.readValue(
                    new ClassPathResource("tips.json").getInputStream(),  // Reference file directly
                    new TypeReference<List<Tip>>() {}
            );

            // Save all tips to the database
            tipRepository.saveAll(tips);
        }
    }
}
