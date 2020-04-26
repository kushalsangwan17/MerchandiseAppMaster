package com.example.merchandiseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;



import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class productdetails extends AppCompatActivity  {

    private static final int RESULT_LOAD_IMAGE = 1; //for verifying that getting image from gallery is succesful
    Button upload_button; // save the image in internal storage of mobile
    String MERCHANT_NAME_KEY="Merchant's_name";
    ImageView Imag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_of_product);
        upload_button = findViewById(R.id.upload_button);
        Imag=findViewById(R.id.uploadedimage);
    }

    public void back_to_merchant(View view) {
        // on click method for confirm product button

        product prod;
        ProductViewModel pp = MainActivity.mproductViewModel;
        EditText colour_b, price_b, width_b, length_b,productname_b,productdes_b;
        length_b = findViewById(R.id.length);
        width_b = findViewById(R.id.width);
        price_b = findViewById(R.id.price);
        colour_b = findViewById(R.id.colour);
        productdes_b=findViewById(R.id.productdes);
        productname_b=findViewById(R.id.productna);

        String length = length_b.getText().toString();
        String width = width_b.getText().toString();
        String price = price_b.getText().toString();
        String colour = colour_b.getText().toString();
        String productname=productname_b.getText().toString();
        String productdes=productdes_b.getText().toString();
        String Merchantname=getIntent().getStringExtra(MERCHANT_NAME_KEY);
        String category = getIntent().getStringExtra("cc"); // men , women or children
        String typename = getIntent().getStringExtra("typename"); // which type of cloth
        String purchased="false"; // purchase status
        if(category!=null && typename!=null && Merchantname!=null)
            prod = new product(category, typename, length, colour, width, price,purchased,Merchantname,productname,productdes);
        else
            prod=null;
        if(prod!=null)
            pp.insert(prod);
        startActivity(new Intent(this, merchant_landing_page.class));
        finish();
    }


    public void onClicku(View v) {

        Intent galleryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryintent, RESULT_LOAD_IMAGE);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedimage = data.getData();
            Imag.setImageURI(selectedimage);
        }

    }




}
