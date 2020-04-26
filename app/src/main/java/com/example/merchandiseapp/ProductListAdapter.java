package com.example.merchandiseapp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private final LayoutInflater mInflater;
    private List<product> mproducts;
    private final OnItemClickListener listener;
    // Cached copy of words

    ProductListAdapter(Context context,OnItemClickListener listener) { mInflater = LayoutInflater.from(context);
            this.listener = listener;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_view_item, parent, false);

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
            holder.bind(current,listener);
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
            productItemName = itemView.findViewById(R.id.productname);
            productItemDescription=itemView.findViewById(R.id.productdescription);
            price=itemView.findViewById(R.id.price);
            productid=itemView.findViewById(R.id.productid);
        }

        private void bind(final product item, final OnItemClickListener listener) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }




}

