package com.example.manillable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.MyViewHolder> {
    private Context context;
    private List<Invoice> mInvoiceList;

    public InvoiceAdapter(Context context) {
        this.context = context;
    }

    public void setmInvoiceList(List<Invoice> mInvoiceList) {
        this.mInvoiceList = mInvoiceList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public InvoiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_card_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.MyViewHolder holder, int position) {
        Invoice invoice = mInvoiceList.get(position);
        holder.clientName.setText(invoice.getClientName());
        holder.amount.setText(invoice.getAmount());
        holder.itemView.setTag(invoice.getId());
    }

    @Override
    public int getItemCount() {
        return this.mInvoiceList.size();
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
