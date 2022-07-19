package com.example.manillable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InvoiceListAdapter extends ListAdapter<Invoice, InvoiceViewHolder> {
    private static final String TAG = "InvoiceAdapter";
    private List<Invoice> mInvoices, mInvoicesFiltered;
    private RecyclerViewClickListener mListener;

    /**
     * Constructor method for ModuleAdapter class
     */
    public InvoiceListAdapter(@NonNull DiffUtil.ItemCallback<Invoice> diffCallback) {
        super(diffCallback);
    }

    @Override
    public InvoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return InvoiceViewHolder.create(parent);
    }


    /**
     * Associate the data with the view holder for a given position within the RecyclerView
     * @param holder - Viewholder parameter
     * @param position - integer value of the record's row in the recycler view
     */
    @Override
    public void onBindViewHolder(InvoiceViewHolder holder, int position) {
        Invoice invoice = getItem(position);
        holder.itemView.setTag(invoice.getmId());
        holder.bind(invoice.getmAmount());
    }

    static class InvoiceDiff extends DiffUtil.ItemCallback<Invoice> {

        @Override
        public boolean areItemsTheSame(@NonNull Invoice oldItem, @NonNull Invoice newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Invoice oldItem, @NonNull Invoice newItem) {
            return oldItem.getmAmount().equals(newItem.getmAmount());
        }
    }
    @Override
    public int getItemCount() {
        return mInvoicesFiltered.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mName, mAmount;
        private RecyclerViewClickListener mListener;

        public ViewHolder(@NonNull View view, RecyclerViewClickListener listener) {
            super(view);
            mListener = listener;
            view.setOnClickListener(this);
            mName = view.findViewById(R.id.txt_Name);
            mAmount = view.findViewById(R.id.txt_Amount);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, (String) view.getTag());
            Log.d(TAG, (String) view.getTag());
        }
    }

    //ClickListener interface
    public interface RecyclerViewClickListener {
        void onClick(View view, String moduleSymbol);
    }

}
