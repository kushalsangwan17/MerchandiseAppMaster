package com.example.merchandiseapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import static com.example.merchandiseapp.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    static public ProductViewModel mproductViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mproductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
    }

    public void goto_login(View view) {
        Intent gotoLoginPage = new Intent(this, com.example.merchandiseapp.Login.class);
        startActivity(gotoLoginPage);
        finish();
    }

    public void goto_signup(View view) {
        Intent gotoSignupPage = new Intent(this, com.example.merchandiseapp.Signin.class);
        startActivity(gotoSignupPage);
        finish();
    }
}
