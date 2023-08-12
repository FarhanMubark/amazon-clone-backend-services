package com.example.amazon_clone_system.Models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Positive
    @NotNull(message = "Id must not be empty")
    @Min(3)
    private Integer id;
    @NotNull(message = "The name must not be empty")
    private String name;
    @Positive
    @NotNull(message = "The price must not be empty")
    private Integer price;
    @Positive
    @NotNull(message = "The category Id must not be empty")
    private Integer categoryId;


}
