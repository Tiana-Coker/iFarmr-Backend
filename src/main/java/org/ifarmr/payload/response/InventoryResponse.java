package org.ifarmr.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private String  itemType;

    private String  name;

    private Integer quantity;

    private Double cost;

    private String location;

    private LocalDate dateAcquired;

    private String  currentState;

    private String photoUrl;
}
