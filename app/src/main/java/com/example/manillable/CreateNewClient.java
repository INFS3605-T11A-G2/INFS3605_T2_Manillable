package com.example.manillable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class CreateNewClient extends AppCompatActivity {
    private static final String TAG = "CreateNewClient";
    private EditText mName, mEmail, mPhone;
    ImageView mClose;
    FloatingActionButton mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_client);

        mName = findViewById(R.id.client_name_client);
        mEmail = findViewById(R.id.client_emailAddress);
        mPhone = findViewById(R.id.clientPhone);
        mClose = findViewById(R.id.iv_closeIcon_client);
        mNext = findViewById(R.id.button_client);

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();

                if(name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(CreateNewClient.this, "Please ensure to fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    addNewClient(name, email, phone);
                }
                finish();
            }
        });


    }

    private void addNewClient(String name, String email, String phone) {
        ClientDatabaseHelper clientDatabaseHelper = ClientDatabaseHelper.getDB(this.getApplicationContext());

        Client client = new Client(name, email, phone);

        clientDatabaseHelper.clientDao().addClient(
                client
        );

    }
}