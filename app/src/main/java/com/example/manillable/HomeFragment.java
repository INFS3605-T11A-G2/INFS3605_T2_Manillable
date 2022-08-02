
package com.example.manillable;

        import android.content.DialogInterface;
        import android.os.Bundle;

        import androidx.appcompat.app.AlertDialog;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.DividerItemDecoration;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;

        import java.util.ArrayList;
        import java.util.List;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    RecyclerView mInvoiceOverDueList, mInvoicePaidList;
    private HomeAdapter homeAdapter;
    private HomeAdapter2 homeAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hometab, container, false);


        initRecyclerView(view);
        initRecyclerView2(view);
        loadInvoiceOverdueList();
        loadInvoicePaidList();


        Log.d(TAG, "View Inflated");
        return view;
    }

    private void initRecyclerView(View view) {
        mInvoiceOverDueList = view.findViewById(R.id.rv_InvoiceListOverdue_home);
        mInvoiceOverDueList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        mInvoiceOverDueList.addItemDecoration(dividerItemDecoration);

        homeAdapter = new HomeAdapter(getActivity().getApplicationContext());

        mInvoiceOverDueList.setAdapter(homeAdapter);

    }

    private void initRecyclerView2(View view) {
        mInvoicePaidList = view.findViewById(R.id.rv_InvoiceListPaid_home);
        mInvoicePaidList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        mInvoicePaidList.addItemDecoration(dividerItemDecoration);

        homeAdapter2 = new HomeAdapter2(getActivity().getApplicationContext());

        mInvoicePaidList.setAdapter(homeAdapter2);

    }

    private void loadInvoiceOverdueList() {
        InvoiceDatabaseHelper invoiceDatabaseHelper = InvoiceDatabaseHelper.getDB(getActivity().getApplicationContext());

        List<Invoice> invoiceOverdueList = invoiceDatabaseHelper.invoiceDao().getUnpaid("Unpaid");

        homeAdapter.setmInvoiceOverdueList(invoiceOverdueList);
    }

    private void loadInvoicePaidList() {
        InvoiceDatabaseHelper invoiceDatabaseHelper = InvoiceDatabaseHelper.getDB(getActivity().getApplicationContext());

        List<Invoice> invoicePaidList = invoiceDatabaseHelper.invoiceDao().getPaid("Paid");

        homeAdapter2.setmInvoicePaidList(invoicePaidList);
    }


}