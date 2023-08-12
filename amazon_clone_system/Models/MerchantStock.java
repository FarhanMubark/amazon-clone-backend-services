package com.example.amazon_clone_system.Models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantStock {
    @Positive
    @NotNull(message = "Id must not be empty")
    @Min(3)
    private Integer id;

    @Positive
    @NotNull(message = "product id should not be null")
    private Integer productId;
    @NotNull(message = "merchant id should not be null")
    private Integer merchantId;
    @Positive
    @NotNull(message = "Stock should not be null")
    @Min(11)
    private Integer stock;

}
