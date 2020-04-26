package com.example.merchandiseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class Displayproducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayproducts);
        String interestedtype=getIntent().getStringExtra("typename");

        RecyclerView recyclerView = findViewById(R.id.recyclerview); // to display all items put on sale by all the merchants
        final ProductListAdapter adapter = new ProductListAdapter(this, new OnItemClickListener() {
            @Override
            public void onItemClick(product item) {
                ProductViewModel pp2= MainActivity.mproductViewModel;
               item.purchased="true";
                pp2.update(item);
                Toast.makeText(getApplicationContext(), " Item Bought", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductViewModel pp= MainActivity.mproductViewModel; // get the database
        pp.gettheproducts(interestedtype).observeForever( new Observer<List<product>>() {
            // observer implements this method as soon as the live data , that is the list of items changes in the database
            @Override
            public void onChanged(@Nullable final List<product> words) {
                //
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);

            }
        });
    }


}
