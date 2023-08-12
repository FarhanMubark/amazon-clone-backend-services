package com.example.amazon_clone_system.Controllers;

import com.example.amazon_clone_system.ApiResponse.ApiResponse;
import com.example.amazon_clone_system.Models.Product;
import com.example.amazon_clone_system.Services.CategoryService;
import com.example.amazon_clone_system.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    final private ProductService productService;
    final private CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getProducts(){
        ArrayList<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProducts(@RequestBody @Valid Product product, Errors errors){
        int categoryId = product.getCategoryId();
        boolean isCateId = categoryService.checkCateId(categoryId);
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (isCateId) {
            productService.addProducts(product);
            return ResponseEntity.status(200).body(new ApiResponse("product is added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category Id Does not Exsit"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProducts(@PathVariable int id,@RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = productService.updateProducts(id,product);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("product is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProducts(@PathVariable int id){
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("product is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }


}
