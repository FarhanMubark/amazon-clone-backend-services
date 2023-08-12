package com.example.amazon_clone_system.Controllers;

import com.example.amazon_clone_system.ApiResponse.ApiResponse;
import com.example.amazon_clone_system.Models.MerchantStock;
import com.example.amazon_clone_system.Services.MerchantService;
import com.example.amazon_clone_system.Services.MerchantStockService;
import com.example.amazon_clone_system.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/merch-stock")
@RequiredArgsConstructor
public class MerchantStockController {
    final private MerchantStockService merchantStockService;
    final private ProductService productService;
    final private MerchantService merchantService;
    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock stock, Errors errors){
        int prodId = stock.getProductId();
        Boolean isProdId = productService.checkProductId(prodId);
        int merchId = stock.getMerchantId();
        Boolean isMerchId = merchantService.checkMerchId(merchId);

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (isProdId && isMerchId) {
            merchantStockService.addMerchantStock(stock);
            return ResponseEntity.status(200).body(new ApiResponse("stock Added !!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Id or Product Id Does not Exsist !"));
    }

    @PutMapping("/update-stocks/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable int id, @RequestBody @Valid MerchantStock stock, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated = merchantStockService.updateMerchantStock(id,stock);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("stock updated !"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Id not fount"));
    }

    @DeleteMapping("/remove-stocks/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id){
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Stock deleted !"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Id Not found !"));
    }

    @PutMapping("/more-stocks/{productId}/{merchId}/{amount}")
    public ResponseEntity aditionalStokcs(@PathVariable int productId, @PathVariable int merchId, @PathVariable int amount){
        boolean isAdded = merchantStockService.additinoalStok(productId, merchId, amount);

        if (isAdded){
            return  ResponseEntity.status(200).body(new ApiResponse("Stocks are added !"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Merchant Id or product Id or amount is wrong, note: amount must be positive number"));

    }
}
