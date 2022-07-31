package com.example.manillable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<InvoiceAdapter.MyViewHolder> {
    private Context context;
    private List<Client> mClientList;

    public ClientAdapter(Context context) {
        this.context = context;
    }

    public void setmClientList(List<Client> mClientList) {
        this.mClientList = mClientList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ClientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_card_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.MyViewHolder holder, int position) {
        holder.name.setText(this.mInvoiceList.get(position).getClientName());
        holder.amount.setText(this.mInvoiceList.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return this.mClientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView clientName, amount;

        public MyViewHolder(@NonNull View view) {
            super(view);
            clientName = view.findViewById(R.id.txt_Name);
            amount = view.findViewById(R.id.txt_Amount);
        }
    }
}
