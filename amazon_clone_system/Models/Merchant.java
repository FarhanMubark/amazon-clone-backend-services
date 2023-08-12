package com.example.amazon_clone_system.Models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    @Positive
    @NotNull(message = "Id must not be empty")
    @Min(3)
    private Integer id;
    @NotEmpty(message = "name Should not be empty")
    private String name;

}
