package org.ifarmr.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsDto {

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Full Name must contain only alphabets and spaces")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "Username must contain at least one alphabet and one number, and only alphabets and numbers are allowed")
    private String username;
}
