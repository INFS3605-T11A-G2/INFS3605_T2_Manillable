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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private static final String TAG = "HomeAdapter";
    private Context context;
    private List<Invoice> mInvoiceOverdueList;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setmInvoiceOverdueList(List<Invoice> mInvoiceOverdueList) {
        this.mInvoiceOverdueList = mInvoiceOverdueList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_card_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, int position) {
        Invoice invoice = mInvoiceOverdueList.get(position);
        holder.clientName.setText(invoice.getClientName());
        holder.amount.setText(invoice.getAmount());
        holder.paid.setText(invoice.getPaid());
        holder.itemView.setTag(String.valueOf(invoice.getId()));
    }

    @Override
    public int getItemCount() {
        return this.mInvoiceOverdueList.size();
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
