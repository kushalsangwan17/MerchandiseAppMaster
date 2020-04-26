package com.example.merchandiseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import models.merchant;
import models.user;

public class Signin extends Activity {
    EditText userName, userEmail ,userPassword, userAddress, merchantName, merchantEmail, merchantPassword;
    FirebaseAuth mFirebaseAuth;
    Button signupButton;
    AppCompatRadioButton userButton, merchantButton;
    TextView alreadyRegistered;
    ConstraintLayout userLayout, merchantLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        mFirebaseAuth = FirebaseAuth.getInstance();

        userLayout = findViewById(R.id.userView);
        userButton = findViewById(R.id.user_selector);
        userName = findViewById(R.id.fullname_edit_text);
        userEmail = findViewById(R.id.username_edit_text);
        userPassword = findViewById(R.id.password_edit_text);
        userAddress = findViewById(R.id.address_edit_text);
        merchantLayout = findViewById(R.id.merchantView);
        merchantButton = findViewById(R.id.merchant_selector);
        merchantName = findViewById(R.id.merchant_name_edit_text);
        merchantEmail = findViewById(R.id.merchant_email_edit_text);
        merchantPassword = findViewById(R.id.merchant_password_edit_text);

        signupButton = findViewById(R.id.signup_button);
        alreadyRegistered = findViewById(R.id.already_registered);
        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this, Login.class));
                finish();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userButton.isChecked()){
                    final String name = userName.getText().toString();
                    final String username = userEmail.getText().toString();
                    final String pwd = userPassword.getText().toString();
                    final String address = userAddress.getText().toString();

                    if(name.isEmpty() || username.isEmpty() || pwd.isEmpty() || address.isEmpty()){
                        Toast.makeText(Signin.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                    }
                    else if(pwd.length()<6){
                        Toast.makeText(Signin.this, "Min Length of Password is 6 !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mFirebaseAuth.createUserWithEmailAndPassword(username,pwd).addOnCompleteListener(Signin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(! task.isSuccessful()){
                                    Toast.makeText(Signin.this,"SignUp Unsuccessful, Please try Again",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    user user = new user(name, username, address);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Signin.this, "SignUp Successful !!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Signin.this, user_landing_page.class));
                                                finish();
                                            }
                                            else{
                                                Toast.makeText(Signin.this,"SignUp Unsuccessful, Please try Again",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
                else if(merchantButton.isChecked()){
                    final String name = merchantName.getText().toString();
                    final String username = merchantEmail.getText().toString();
                    String pwd = merchantPassword.getText().toString();

                    if(name.isEmpty() || username.isEmpty() || pwd.isEmpty()){
                        Toast.makeText(Signin.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                    }
                    else if(pwd.length()<6){
                        Toast.makeText(Signin.this, "Min Length of Password is 6 !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mFirebaseAuth.createUserWithEmailAndPassword(username,pwd).addOnCompleteListener(Signin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(! task.isSuccessful()){
                                    Toast.makeText(Signin.this,"SignUp Unsuccessful, Please try Again",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    merchant merchant = new merchant(name, username);
                                    FirebaseDatabase.getInstance().getReference("Merchants")
                                            .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                            .setValue(merchant).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Signin.this, "SignUp Successful !!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Signin.this,merchant_landing_page.class));
                                                finish();
                                            }
                                            else{
                                                Toast.makeText(Signin.this,"SignUp Unsuccessful, Please try Again",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });

        userButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(userButton.isChecked()){
                    merchantButton.setChecked(false);
                    userLayout.setVisibility(View.VISIBLE);
                    merchantLayout.setVisibility(View.GONE);
                }
            }
        });

        merchantButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(merchantButton.isChecked()){
                    userButton.setChecked(false);
                    merchantLayout.setVisibility(View.VISIBLE);
                    userLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    public void back_to_main(View view) {
        Intent gotoMainPage = new Intent(this, MainActivity.class);
        startActivity(gotoMainPage);
        finish();
    }
}
