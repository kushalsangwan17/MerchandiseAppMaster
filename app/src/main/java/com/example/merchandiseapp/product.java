package com.example.merchandiseapp;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class product {
    @PrimaryKey(autoGenerate = true)

    public int productkey;



    @NonNull
    public String category , type_of_wear,colour,length,width,price,purchased,Merchantname,productname,productdes;

    public product(@NonNull String category, @NonNull String type_of_wear, @NonNull String colour, @NonNull String length, @NonNull String width,@NonNull String price ,@NonNull String purchased,@NonNull String Merchantname,@NonNull String productname,@NonNull String productdes )

    {
        this.purchased=purchased;
        this.category=category;
        this.type_of_wear=type_of_wear;
        this.length=length;
        this.colour=colour;
        this.width=width;
        this.price=price;
        this.Merchantname=Merchantname;
        this.productdes=productdes;
        this.productname=productname;
    }

}
