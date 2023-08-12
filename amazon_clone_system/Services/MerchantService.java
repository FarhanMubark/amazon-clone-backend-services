package com.example.amazon_clone_system.Services;

import com.example.amazon_clone_system.Models.Category;
import com.example.amazon_clone_system.Models.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    ArrayList<Merchant> merchants = new ArrayList<>();
    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    //C
    public void addMerchants(Merchant merchant){
        merchants.add(merchant);
    }

    public Boolean updateMerchants(int id, Merchant merchant){
        for (int i=0; i<merchants.size();i++){
            if (merchants.get(i).getId()==id){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

//    public Boolean checkCateId(int cateId){
//        for (Category category:categories){
//            if (category.getId()==cateId){
//                return true;
//            }
//        }
//        return false;
//    }
    public Boolean checkMerchId(int merchId){
        for (Merchant merchant:merchants){
            if (merchant.getId()==merchId){
                return true;
            }
        }
        return false;
    }

    public Boolean deleteMerchants(int id){
        for (int i=0; i<merchants.size();i++){
            if (merchants.get(i).getId()==id){
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }
}
