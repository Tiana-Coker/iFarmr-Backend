package org.ifarmr.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ifarmr.enums.ItemType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequest {


    @NotNull(message = "Item Type is required")
    private ItemType itemType;

    @NotBlank(message = "Title is required")
    private String  name;

    @NotBlank(message = "Quantity is required")
    private Integer quantity;

    @NotBlank(message = "Cost is required")
    private Double cost;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Date acquired is required")
    private LocalDate dateAcquired;

    @NotBlank(message = "Current state is required")
    private String  currentState;

    private MultipartFile file;
}
