package com.example.amazon_clone_system.Models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Positive
    @NotNull(message = "Id must not be empty")
    @Min(3)
    private Integer id;
    @NotEmpty(message = "The name should not be empty")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only characters and numbers are allowed")
    private String password;
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @NotEmpty
    @Pattern(regexp = "Admin|Customer",message = "Role must be Admin or Customer")
    private String role;
    @Positive
    @NotNull(message = "balance should not be null")
    private Integer balance;
}


