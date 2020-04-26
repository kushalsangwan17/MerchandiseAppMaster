package com.example.merchandiseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class user_landing_page extends Activity {
    FirebaseAuth mFirebaseAuth;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_landing_page);


        mFirebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


    }

    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(user_landing_page.this, "Signed Out Successfully !", Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(user_landing_page.this,MainActivity.class));
        finish();
    }

    public void clothes_buy(View view) {
        Intent intent2= new Intent(user_landing_page.this,add_apparels.class); // leads to choose between men/women/children
        intent2.putExtra("openforwhom","user"); // added to recognize who is opening the aparels page user or merchant
        startActivity(intent2);
    }
}
