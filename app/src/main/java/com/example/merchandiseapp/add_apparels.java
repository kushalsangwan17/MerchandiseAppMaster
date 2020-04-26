package com.example.merchandiseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class add_apparels extends Activity {
    // choose from men , women or children activity




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_apparels);
    }

    public void back_to_merchant_page(View view) {
        startActivity(new Intent(add_apparels.this, merchant_landing_page.class));
        finish();
    }

    public void clickCategory(View view) {
         String categorykey="cc";
        int id=view.getId();
        Intent intent=new Intent(add_apparels.this, type_of_wear.class);
        switch(id){
            case R.id.men :
                intent.putExtra(categorykey,"men");
                break;
            case R.id.women :
                intent.putExtra(categorykey,"women");
                break;
            case R.id.children :
                intent.putExtra(categorykey,"children");
                break;

        }
        String MERCHANT_NAME_KEY="Merchant's_name";
        intent.putExtra(MERCHANT_NAME_KEY,getIntent().getStringExtra(MERCHANT_NAME_KEY));
        String user_merchant=getIntent().getStringExtra("openforwhom");
        intent.putExtra("openforwhom",user_merchant);
        startActivity(intent);
        finish();
    }
}
