package org.ifarmr.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification  extends BaseClass{

    private String content;

    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("inventory-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;

}
