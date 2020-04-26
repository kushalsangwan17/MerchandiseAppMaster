package com.example.merchandiseapp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ProductSoldListAdapter extends RecyclerView.Adapter<ProductSoldListAdapter.ProductViewHolder> {


    private final LayoutInflater mInflater;
    private List<product> mproducts;
    // Cached copy of words

    ProductSoldListAdapter(Context context) { mInflater = LayoutInflater.from(context);

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyler_view_itemsold, parent, false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (mproducts != null) {
            product current = mproducts.get(position);
            String productname,productdes,pric;
            String productid;
            productname=current.productname;
            productdes=current.productdes;
            productid=Integer.toString(current.productkey);
            pric=current.price;

            holder.productItemName.setText(productname);
            holder.productItemDescription.setText(productdes);
            holder.price.setText(pric);
            holder.productid.setText(productid);
        }
    }

    void setWords(List<product> words){
        mproducts = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mproducts != null)
            return mproducts.size();
        else return 0;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productItemName,productItemDescription,price,productid;
        private ProductViewHolder(View itemView) {
            super(itemView);
            productItemName = itemView.findViewById(R.id.productname2);
            productItemDescription=itemView.findViewById(R.id.productdescription2);
            price=itemView.findViewById(R.id.price2);
            productid=itemView.findViewById(R.id.productid2);
        }


    }




}
