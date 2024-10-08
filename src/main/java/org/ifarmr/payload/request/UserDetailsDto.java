package org.ifarmr.payload.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.ifarmr.enums.Gender;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsDto {

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Full Name must contain only alphabets and spaces")
    private String fullName;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "Username must contain at least one alphabet and one number, and only alphabets and numbers are allowed")
    private String username;

    private Gender gender;

    private String profilePictureUrl;

    private String bio;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "Password must contain at least one alphabet and one number, and only alphabets and numbers are allowed")
    private String password;

    private  String email;

}
