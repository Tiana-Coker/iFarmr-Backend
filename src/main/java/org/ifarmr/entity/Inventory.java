package org.ifarmr.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory_tbl")
@Builder
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
