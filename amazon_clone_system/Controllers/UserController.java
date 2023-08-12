package com.example.amazon_clone_system.Controllers;

import com.example.amazon_clone_system.ApiResponse.ApiResponse;
import com.example.amazon_clone_system.Models.User;
import com.example.amazon_clone_system.Services.MerchantService;
import com.example.amazon_clone_system.Services.MerchantStockService;
import com.example.amazon_clone_system.Services.ProductService;
import com.example.amazon_clone_system.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;
    final private ProductService productService;
    final private MerchantService merchantService;
    final private MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        ArrayList<User> users = userService.getUser();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUsers(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUsers(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Added !!"));
    }

    @PutMapping("/update-users/{id}")
    public ResponseEntity updateUsers(@PathVariable int id, @RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated = userService.updateUsers(id,user);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated !"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not fount"));
    }

    @DeleteMapping("/remove-user/{id}")
    public ResponseEntity deleteUsers(@PathVariable int id){
        boolean isDeleted = userService.deleteUsers(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted !"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User Id Not found !"));
    }


    @PutMapping("/buy-product/{userId}/{productId}/{merchId}")
    public ResponseEntity buyProduct(@PathVariable int userId,@PathVariable int productId, @PathVariable int merchId) {
        boolean isUserIdValid = userService.checkUserId(userId);
        boolean isProductIdValid = productService.checkProductId(productId);
        boolean isMerchIdValid = merchantService.checkMerchId(merchId);

        if (!isUserIdValid || !isProductIdValid || !isMerchIdValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid user, product, or merchant ID"));
        }


        int merchantStock = merchantStockService.fetchStock(productId, merchId);
        if (merchantStock <= 0 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Product is out of stock"));
        }

        int userBalance = userService.getUserBalance(userId);


        int productPrice = productService.getProductPrice(productId);
        if (userBalance < productPrice) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Not enough balance !"));
        }


        int remainingBalance = userBalance - productPrice;
        userService.updateUserBalance(userId, remainingBalance);


        merchantStockService.reduceStock(productId, merchId);

        return ResponseEntity.status(200).body(new ApiResponse("Product purchased successfully"));
    }

    // Extra endpoint
    @PutMapping("/add-tocart/{userId}/{productId}")
    public ResponseEntity addToCart(@PathVariable int userId,@PathVariable int productId){
        boolean isUserId = userService.checkUserId(userId);
        boolean isProductId = productService.checkProductId(productId);

        if(isUserId && isProductId){
            return ResponseEntity.status(200).body(new ApiResponse("Product Added successfully"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("product Id or user Id Wrong !"));

    }

}




