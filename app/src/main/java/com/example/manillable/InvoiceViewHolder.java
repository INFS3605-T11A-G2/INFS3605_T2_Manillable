package com.example.manillable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InvoiceViewHolder extends RecyclerView.ViewHolder {
    private final TextView invoiceItemView;

    public InvoiceViewHolder(@NonNull View itemView) {
        super(itemView);
        invoiceItemView = itemView.findViewById(R.id.txt_Name);
    }
    public void bind(String text) {
        invoiceItemView.setText(text);
    }

    static InvoiceViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.invoice_card_row, parent, false);
        return new InvoiceViewHolder(view);
    }
}
