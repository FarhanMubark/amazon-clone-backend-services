package com.example.amazon_clone_system.Services;

import com.example.amazon_clone_system.Models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    //R
    public ArrayList<Category> getCategories() {
        return categories;
    }

    //C
    public void addCategories(Category category){
        categories.add(category);
    }

    public Boolean updateCategories(int id, Category category){
        for (int i=0; i<categories.size();i++){
            if (categories.get(i).getId()==id){
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteCategories(int id){
        for (int i=0; i<categories.size();i++){
            if (categories.get(i).getId()==id){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    public Boolean checkCateId(int cateId){
        for (Category category:categories){
            if (category.getId()==cateId){
                return true;
            }
        }
        return false;
    }
}
