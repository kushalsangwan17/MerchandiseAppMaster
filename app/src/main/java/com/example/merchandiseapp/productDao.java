package com.example.merchandiseapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface productDao {
    @Insert
    void insert(product prod);

    @Delete
    void delete(product prod);

    @Update
    void update(product prod);

    @Query("SELECT * from product_table where type_of_wear=:interestedtype ")
    LiveData<List<product>> gettheproducts(String interestedtype);

    @Query("SELECT * from product_table where Merchantname=:merchantName AND purchased=\"true\" ")
    LiveData<List<product>> getsoldproducts(String merchantName);

    @Query("DELETE FROM product_table")
    void deleteall();
}
