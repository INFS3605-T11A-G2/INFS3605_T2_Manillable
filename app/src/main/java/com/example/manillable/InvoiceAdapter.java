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

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.MyViewHolder> {
    private static final String TAG = "InvoiceAdapter";
    private Context context;
    private List<Invoice> mInvoiceList;
    private RecyclerViewClickListener mListener;

    public InvoiceAdapter(Context context, RecyclerViewClickListener mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    public void setmInvoiceList(List<Invoice> mInvoiceList) {
        this.mInvoiceList = mInvoiceList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public InvoiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invoice_card_row, parent, false);

        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.MyViewHolder holder, int position) {
        Invoice invoice = mInvoiceList.get(position);
        holder.clientName.setText(invoice.getClientName());
        holder.amount.setText(invoice.getAmount());
        holder.paid.setText(invoice.getPaid());
        holder.itemView.setTag(String.valueOf(invoice.getId()));
    }

    @Override
    public int getItemCount() {
        return this.mInvoiceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView clientName, amount, paid;
        private RecyclerViewClickListener mListener;

        public MyViewHolder(@NonNull View view, RecyclerViewClickListener listener) {
            super(view);
            mListener = listener;
            view.setOnClickListener(this);
            clientName = view.findViewById(R.id.txt_Name_clientRow);
            amount = view.findViewById(R.id.txt_Amount);
            paid = view.findViewById(R.id.txt_paid);

        }
        @Override
        public void onClick(View view) {
            mListener.onClick(view, (String) view.getTag());
            Log.d(TAG, (String) view.getTag());
        }
    }



    //ClickListener interface
    public interface RecyclerViewClickListener {
        void onClick(View view, String invoiceSymbol);
    }
}
