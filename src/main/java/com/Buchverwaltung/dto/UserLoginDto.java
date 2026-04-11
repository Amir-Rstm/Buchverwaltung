package com.Buchverwaltung.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {

    @NotBlank(message = "username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

}
