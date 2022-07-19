package com.example.manillable;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvoiceFragment extends Fragment {
    private static final String TAG = "InvoiceFragment";
    private RecyclerView mRecyclerView;
    private InvoiceListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private InvoiceViewModel mInvoiceViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvoiceFragment() {
        // Required empty public constructor
    }
//
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvoicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvoiceFragment newInstance(String param1, String param2) {
        InvoiceFragment fragment = new InvoiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//        mInvoiceViewModel = new ViewModelProvider(this).get(InvoiceViewModel.class);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_invoices, container, false);
//
//        mRecyclerView = view.findViewById(R.id.rv_InvoiceList);
//        mRecyclerView.setHasFixedSize(true);
//
//        mLayoutManager = new LinearLayoutManager(view.getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        InvoiceListAdapter.RecyclerViewClickListener listener = new InvoiceListAdapter.RecyclerViewClickListener() {
//            @Override
//            public void onClick(View view, String invoiceSymbol) {
//                Log.d(TAG, invoiceSymbol);
//                launchDetailActivity(invoiceSymbol);
//            }
//        };
//
//        mAdapter = new InvoiceListAdapter(new InvoiceListAdapter.InvoiceDiff());
//        mRecyclerView.setAdapter(mAdapter);
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_invoices, container, false);
//    }
//
//    private void launchDetailActivity(String invoiceSymbol) {
//        Log.d(TAG, "Detail Activity Launched");
//    }
}