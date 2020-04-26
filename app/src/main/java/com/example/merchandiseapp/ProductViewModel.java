package com.example.merchandiseapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mRepository;

    private LiveData<List<product>> mAllproducts; // products available of a certain type
    private LiveData<List<product>> msoldproducts; // product sold by a particular merchant

    public ProductViewModel (Application application) {
        super(application);
        mRepository = new ProductRepository(application);
    }

    LiveData<List<product>> gettheproducts(String interestedtype) {
        mAllproducts = mRepository.gettheproducts(interestedtype);
        return mAllproducts; }

    LiveData<List<product>> getsoldproducts(String merchantName) {
        msoldproducts = mRepository.getsoldproducts(merchantName);
        return msoldproducts; }

    public void insert(product product) { mRepository.insert(product); }
    public void update(product product) { mRepository.update(product); }
    public void delete(product product) { mRepository.delete(product); }
    public void deleteall(){mRepository.deleteall();}
}
