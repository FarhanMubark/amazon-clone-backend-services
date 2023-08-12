package com.example.amazon_clone_system.Services;


import com.example.amazon_clone_system.Models.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
   ArrayList<MerchantStock> stocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStock() {
        return stocks;
    }


    public void addMerchantStock(MerchantStock stock){
        stocks.add(stock);
    }

    public Boolean updateMerchantStock(int id, MerchantStock stock){
        for (int i=0; i<stocks.size();i++){
            if (stocks.get(i).getId()==id){
                stocks.set(i,stock);
                return true;
            }
        }
        return false;
    }

    public Boolean additinoalStok(int productId, int merchId, int amount ){
        for (int i=0;i<stocks.size();i++){
            MerchantStock stock = stocks.get(i);
            if (stock.getProductId() == productId && stock.getMerchantId() == merchId && amount > 0){
                int currentAmount = stocks.get(i).getStock();
                stock.setStock(currentAmount + amount);
                return true;
            }
        }
        return false;
    }

    public Boolean reduceStock(int productId, int merchId){
        for (int i=0; i< stocks.size();i++){
            MerchantStock stock = stocks.get(i);
            if (stock.getProductId()==productId && stock.getMerchantId() == merchId){
                stock.setStock(stock.getStock()-1);
                return true;
            }
        }
        return false;
    }

    public int fetchStock(int productId, int merchId){

        for (int i =0;i<stocks.size();i++){
            MerchantStock stock = stocks.get(i);
            if (stock.getProductId() == productId && stock.getMerchantId()== merchId){

                     return stock.getStock();
            }
        }
        return 0;
    }

    public Boolean deleteMerchantStock(int id){
        for (int i=0; i<stocks.size();i++){
            if (stocks.get(i).getId()==id){
                stocks.remove(i);
                return true;
            }
        }
        return false;
    }
}
