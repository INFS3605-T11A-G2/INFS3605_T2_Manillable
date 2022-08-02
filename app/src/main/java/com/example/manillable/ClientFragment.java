package com.example.manillable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class ClientFragment extends Fragment {
    private static final String TAG = "ClientFragment";
    FloatingActionButton fab;
    RecyclerView mClientList;
    private ClientAdapter clientAdapter;
    private boolean isDone = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientstab, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_clients);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity().getApplicationContext(), CreateNewInvoice.class), 100);
            }
        });

        initRecyclerView(view);
        loadClientList();

        return view;
    }
    private void initRecyclerView(View view) {
        mClientList = view.findViewById(R.id.rv_clientList);
        mClientList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        mClientList.addItemDecoration(dividerItemDecoration);

        clientAdapter = new ClientAdapter(getActivity().getApplicationContext());

        mClientList.setAdapter(clientAdapter);

    }

    private void loadClientList() {
        ClientDatabaseHelper clientDatabaseHelper = ClientDatabaseHelper.getDB(getActivity().getApplicationContext());
        insertClients();
        List<Client> clientList = clientDatabaseHelper.clientDao().getAllClient();

        clientAdapter.setmClientList(clientList);
    }

    private void insertClients() {
        ClientDatabaseHelper clientDatabaseHelper = ClientDatabaseHelper.getDB(getActivity().getApplicationContext());
        if(!isDone) {
            clientDatabaseHelper.clientDao().addClient(new Client("Patrick Diakos", "p.diakos@gmail.com", "0417498573"));
            clientDatabaseHelper.clientDao().addClient(new Client("Bradley Driscoll", "b.driscoll@gmail.com", "0495876615"));
            clientDatabaseHelper.clientDao().addClient(new Client("Nevin Liu", "n.liu@gmail.com", "0495876615"));
            isDone = true;
        }
        Log.d(TAG, String.valueOf(isDone));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            loadClientList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
