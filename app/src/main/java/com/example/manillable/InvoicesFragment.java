package com.example.manillable;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InvoicesFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView mInvoiceList;
    private InvoiceAdapter invoiceAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoicestab, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateNewInvoice.class);
                startActivity(intent);
            }
        });

        initRecyclerView(view);
        loadInvoiceList();

        return view;
    }
    private void initRecyclerView(View view) {
        mInvoiceList = view.findViewById(R.id.rv_InvoiceList);
        mInvoiceList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        mInvoiceList.addItemDecoration(dividerItemDecoration);

        invoiceAdapter = new InvoiceAdapter(getActivity().getApplicationContext());

        mInvoiceList.setAdapter(invoiceAdapter);

    }

    private void loadInvoiceList() {
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(getActivity().getApplicationContext());

        List<Invoice> invoiceList = databaseHelper.invoiceDao().getAllInvoice();

        invoiceAdapter.setmInvoiceList(invoiceList);
    }
}
