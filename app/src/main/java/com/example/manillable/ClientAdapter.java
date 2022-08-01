package com.example.manillable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder> {
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
        View view = LayoutInflater.from(context).inflate(R.layout.client_card_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientAdapter.MyViewHolder holder, int position) {
        holder.clientName.setText(this.mClientList.get(position).getName());
        holder.phone.setText(this.mClientList.get(position).getPhone());
        holder.email.setText(this.mClientList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return this.mClientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView clientName, phone, email;

        public MyViewHolder(@NonNull View view) {
            super(view);
            clientName = view.findViewById(R.id.txt_Name_clientRow);
            phone = view.findViewById(R.id.txt_phone_clientRow);
            email = view.findViewById(R.id.txt_email_clientRow);
        }
    }
}
