package com.example.manillable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CreateNewInvoice extends AppCompatActivity {
    private static final String TAG = "CreateNewInvoice";
    EditText editName, editAmount;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_invoice);

        editName = findViewById(R.id.client_name);
        editAmount = findViewById(R.id.amount);
        submitButton = findViewById(R.id.button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewInvoice(editName.getText().toString(), editAmount.getText().toString());
            }
        });
    }

    private void addNewInvoice(String clientName, String amount) {
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this.getApplicationContext());

        databaseHelper.invoiceDao().addInvoice(
                new Invoice(clientName, "item", "itemQuant","itemEa",amount,"paid")
        );

        ArrayList<Invoice> arrInvoices = (ArrayList<Invoice>) databaseHelper.invoiceDao()
                .getAllInvoice();

        for(int i = 0; i<arrInvoices.size(); i++) {
            Log.d(TAG, "Client Name: " + arrInvoices.get(i).getClientName() +
                    " Amount: " + arrInvoices.get(i).getAmount());
        }

        finish();
    }
}