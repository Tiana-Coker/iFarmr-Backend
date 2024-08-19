package org.ifarmr.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory extends BaseClass{

    private String name;

    private String itemType;

    private Integer quantity;

    private Integer cost;

    private String photoUpload;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("inventory-user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
}
