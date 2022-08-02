package com.example.manillable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter2 extends RecyclerView.Adapter<HomeAdapter2.MyViewHolder> {
    private static final String TAG = "HomeAdapter2";
    private Context context;
    private List<Invoice> mInvoicePaidList;

    public HomeAdapter2(Context context) {
        this.context = context;
    }

    public void setmInvoicePaidList(List<Invoice> mInvoicePaidList) {
        this.mInvoicePaidList = mInvoicePaidList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HomeAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_card_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter2.MyViewHolder holder, int position) {
        Invoice invoice = mInvoicePaidList.get(position);
        holder.clientName.setText(invoice.getClientName());
        holder.amount.setText(invoice.getAmount());
        holder.paid.setText(invoice.getPaid());
        holder.itemView.setTag(String.valueOf(invoice.getId()));
    }

    @Override
    public int getItemCount() {
        return this.mInvoicePaidList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView clientName, amount, paid;

        public MyViewHolder(@NonNull View view) {
            super(view);
            clientName = view.findViewById(R.id.txt_Name_invoiceRow);
            amount = view.findViewById(R.id.txt_Amount);
            paid = view.findViewById(R.id.txt_paid);
        }
    }
}
