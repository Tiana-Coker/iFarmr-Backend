package org.ifarmr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.ifarmr.enums.Status;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "ticket_tbl")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BaseClass {


    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("ticket-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

    @ManyToOne
    @JsonBackReference
    private User admin;

}
