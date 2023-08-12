package com.example.amazon_clone_system.Services;


import com.example.amazon_clone_system.Models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void addProducts(Product product){

        products.add(product
        );
    }

    public Boolean updateProducts(int id, Product product){
        for (int i =0; i<products.size();i++){
            if (products.get(i).getId()==id){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }
    public Boolean deleteProduct(int id){
        for (int i =0; i<products.size();i++){
            if (products.get(i).getId()==id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getProductPrice(int productId){
        for (int i=0; i<products.size();i++){
            Product product = products.get(i);
            if (product.getId()==productId){
                 int productPrice = product.getPrice();
                 return productPrice;
            }
        }
        return 0;
    }

    public Product getProductId(int productId){
        for (int i=0;i<products.size();i++){
            Product product = products.get(i);
            if (product.getId()==productId){
                return product;
            }
        }
        return null;
    }

    public Boolean checkProductId(int productId){
        for (Product product:products){
            if (product.getId()==productId){
                return true;
            }
        }
        return false;
    }
}
