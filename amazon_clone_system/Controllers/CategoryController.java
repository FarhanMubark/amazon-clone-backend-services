package com.example.amazon_clone_system.Controllers;
import com.example.amazon_clone_system.ApiResponse.ApiResponse;
import com.example.amazon_clone_system.Models.Category;
import com.example.amazon_clone_system.Services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    final private CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategories(){
        ArrayList<Category> categories = categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity addCategories(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategories(category);
        return ResponseEntity.status(200).body(new ApiResponse("category is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategories(@PathVariable int id, @RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = categoryService.updateCategories(id,category);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("category is updated"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("id not found "));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategories(@PathVariable int id){
        boolean isDeleted  = categoryService.deleteCategories(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("category deleted !"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Id Not found !"));
    }
}
