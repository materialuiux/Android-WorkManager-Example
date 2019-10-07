package com.materialuiux.androidworkmanagerexample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.materialuiux.androidworkmanagerexample.R;
import com.materialuiux.androidworkmanagerexample.model.Quotes;

import java.util.ArrayList;

public class Ad_Quotes extends RecyclerView.Adapter<Ad_Quotes.ViewHolder> {

    Context mContext;
    ArrayList<Quotes> dataList;

    public Ad_Quotes(@NonNull Context context, ArrayList<Quotes> quotesArrayList) {
        mContext = context;
        dataList = quotesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Quotes quotes = dataList.get(position);
        holder.tx_publisher.setText(quotes.getPublisher());
        holder.tx_quotes.setText(quotes.getQuotes());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tx_publisher, tx_quotes;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_publisher = itemView.findViewById(R.id.publisher);
            tx_quotes = itemView.findViewById(R.id.quotes);
        }
    }
}
