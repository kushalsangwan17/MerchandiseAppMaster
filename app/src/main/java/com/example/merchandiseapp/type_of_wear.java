package com.example.merchandiseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class type_of_wear extends AppCompatActivity {

    ImageButton sub1,sub2,sub3,sub4,sub5,sub6; // for the six subcategories in the men,women or children section
    String choice; // out of men,women and children what was chosen at the first page
    Intent intent2 ;    // to lead to product details activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_of_wear);
        intent2= new Intent(this, productdetails.class); // to take to detail filling section

        sub1 = findViewById(R.id.type1);
        sub2 = findViewById(R.id.type2);
        sub3 = findViewById(R.id.type3);
        sub4 = findViewById(R.id.type4);
        sub5 = findViewById(R.id.type5);
        sub6 = findViewById(R.id.type6);
        Intent intent = getIntent();
        choice = intent.getStringExtra("cc"); // cc was key for men/women choice
        intent2.putExtra("cc", choice); // children , women or men category

        // switch to set the images according to wether child , women or men was chosen
        if (choice != null)
            // this sets the subcategories according to whether it is saree,lehenga etc. for women or jeans,shirt for men
            switch (choice) {
                case "men":
                    sub1.setImageResource(R.drawable.sweater);
                    sub2.setImageResource(R.drawable.jeans);
                    sub3.setImageResource(R.drawable.shirt);
                    sub4.setImageResource(R.drawable.tshirt);
                    sub5.setImageResource(R.drawable.suit);
                    sub6.setImageResource(R.drawable.hoodie);
                    break;
                case "women":
                    sub1.setImageResource(R.drawable.vintage);
                    sub2.setImageResource(R.drawable.saree);
                    sub3.setImageResource(R.drawable.suitlady);
                    sub4.setImageResource(R.drawable.lehenga);
                    sub5.setImageResource(R.drawable.top);
                    sub6.setImageResource(R.drawable.one_piece);
                    break;
                case "children":
                    sub1.setImageResource(R.drawable.floral2);
                    sub2.setImageResource(R.drawable.frock);
                    sub3.setImageResource(R.drawable.gown);
                    sub4.setImageResource(R.drawable.kidhoodie);
                    sub5.setImageResource(R.drawable.tshirt_pant);
                    sub6.setImageResource(R.drawable.barbie);
                    break;
            }
    }

    // on click method when one of the subcategories is chosen
    public void go_to_details(View view) {
        int typeid = view.getId();
        String typenamekey = "typename";
        String typevalue="suit";


        switch (choice) {
            case "men":
                switch (typeid) {
                    case R.id.type1:
                        typevalue = "sweater";
                        break;
                    case R.id.type2:
                        typevalue = "jeans";
                        break;
                    case R.id.type3:
                        typevalue = "shirt";
                        break;
                    case R.id.type4:
                        typevalue = "tshirt";
                        break;
                    case R.id.type5:
                        typevalue = "suit";
                        break;
                    case R.id.type6:
                        typevalue = "hoodie";
                        break;
                }
                break;
            case "women":
                switch (typeid) {
                    case R.id.type1:
                        typevalue = "vintage";
                        break;
                    case R.id.type2:
                        typevalue = "saree";
                        break;
                    case R.id.type3:
                        typevalue = "suitlady";
                        break;
                    case R.id.type4:
                        typevalue = "lehenga";
                        break;
                    case R.id.type5:
                        typevalue = "top";
                        break;
                    case R.id.type6:
                        typevalue = "one_piece";
                        break;
                }
                break;


            case "children":
                switch (typeid) {
                    case R.id.type1:
                        typevalue = "floral";
                        break;
                    case R.id.type2:
                        typevalue = "frock";
                        break;
                    case R.id.type3:
                        typevalue = "gown";
                        break;
                    case R.id.type4:
                        typevalue = "kidhoodie";
                        break;
                    case R.id.type5:
                        typevalue = "tshirt_pant";
                        break;
                    case R.id.type6:
                        typevalue = "barbie";
                        break;
                }
                break;

        }

        intent2.putExtra(typenamekey,typevalue);

        /* merchant's name is considered to be unique for now when finding which
        of his products have been sold*/
        String MERCHANT_NAME_KEY="Merchant's_name";

        intent2.putExtra(MERCHANT_NAME_KEY,getIntent().getStringExtra(MERCHANT_NAME_KEY)); // leads to sell products page

        Intent intent3 = new Intent(this,Displayproducts.class);    // leads to buy products page
        intent3.putExtra(typenamekey,typevalue);

        String user_merchant=getIntent().getStringExtra("openforwhom");

        if(user_merchant!=null && user_merchant.equals("merchant"))
            startActivity(intent2);
        else
            startActivity(intent3);
        finish();

    }
}
