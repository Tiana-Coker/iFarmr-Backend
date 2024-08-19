package org.ifarmr.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Data
@EnableJpaAuditing
@MappedSuperclass
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;
    @PrePersist
    @PreUpdate
    //This allows us to persist and allow the created date to hit the database
    public void prePersist(){
        if (dateCreated == null){
            dateCreated = LocalDateTime.now();
        }
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode(): 0;
    }
}
