package com.example.amazon_clone_system.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Positive
    @NotNull(message = "id must not be null")
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 4)
    private String name;
}
