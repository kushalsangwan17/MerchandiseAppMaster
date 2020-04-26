package com.example.merchandiseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Objects;

import models.merchant;

public class merchant_landing_page extends Activity {
    FirebaseAuth mFirebaseAuth;
    GoogleSignInClient mGoogleSignInClient;
    TextView merchantName;
    String mName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_landing_page);

        mFirebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        merchantName = findViewById(R.id.company_name_text_view);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Merchants").child(Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mName = Objects.requireNonNull(dataSnapshot.getValue(merchant.class)).getName();
                merchantName.setText(mName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        final ProductSoldListAdapter adapter = new ProductSoldListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductViewModel pp= MainActivity.mproductViewModel; // get the database
        pp.getsoldproducts(mName).observeForever( new Observer<List<product>>() {
            // observer implements this method as soon as the live data , that is the list of items changes in the database
            @Override
            public void onChanged(@Nullable final List<product> words) {
                //
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);

            }
        });

    }

    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(merchant_landing_page.this, "Signed Out Successfully !", Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(merchant_landing_page.this,MainActivity.class));
        finish();
    }

    public void goto_add_page(View view) {
        Intent intent=new Intent(merchant_landing_page.this, add_apparels.class).putExtra("Merchant's_name", mName);
        intent.putExtra("openforwhom","merchant");
        startActivity(intent);
        finish();
    }
}
