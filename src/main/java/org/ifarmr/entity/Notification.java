package org.ifarmr.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification_tbl")
@Builder
public class Notification  extends BaseClass{

    private String content;

    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("inventory-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

}
