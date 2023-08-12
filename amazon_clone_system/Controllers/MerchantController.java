package com.example.amazon_clone_system.Controllers;
import com.example.amazon_clone_system.ApiResponse.ApiResponse;
import com.example.amazon_clone_system.Models.Merchant;
import com.example.amazon_clone_system.Services.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    final private MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchants(){
        ArrayList<Merchant> merchants = merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchants);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchants(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchants(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Added !!"));
    }

    @PutMapping("/update-merchants/{id}")
    public ResponseEntity updateMerchants(@PathVariable int id, @RequestBody @Valid Merchant merchant,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated = merchantService.updateMerchants(id, merchant);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchants updated !"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Id not fount"));
    }

    @DeleteMapping("/remove-merchants/{id}")
    public ResponseEntity deleteMerchants(@PathVariable int id){
        boolean isDeleted = merchantService.deleteMerchants(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Merchants deleted !"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Id Not found !"));
    }
}
