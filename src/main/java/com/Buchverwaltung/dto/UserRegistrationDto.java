package com.Buchverwaltung.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {

    @NotBlank(message = "Username is not allowed to be empty!")
    @Size(min = 3, max = 20, message = "Username must has include between 3 and 20 characters")
    private String username;

    @NotBlank(message = "password can not be empty!")
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;

    @Email(message = "Email is invalid")
    private String email;

}
