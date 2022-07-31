package com.example.manillable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateNewInvoice extends AppCompatActivity {
    private static final String TAG = "CreateNewInvoice";
    EditText editName, editItem, editItemQuant, editItemEa, editAmount, editDueDate;
    FloatingActionButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_invoice);

        editName = findViewById(R.id.client_name);
        editItem = findViewById(R.id.item);
        editItemQuant = findViewById(R.id.itemQuant);
        editItemEa = findViewById(R.id.itemEa);
        editAmount = findViewById(R.id.amount);
        editDueDate = findViewById(R.id.dueDate);
        submitButton = findViewById(R.id.button);



        editItemEa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!editItemEa.getText().toString().isEmpty() && !editItemQuant.getText().toString().isEmpty()) {
                    int numItemQuant = Integer.parseInt(editItemQuant.getText().toString());
                    int numItemEa = Integer.parseInt(editItemEa.getText().toString());
                    int itemAmount = numItemQuant * numItemEa;
                    editAmount.setText(String.valueOf("$ " + itemAmount));
                } else {
                    editAmount.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editDueDate.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    editDueDate.setText(current);
                    editDueDate.setSelection(sel < current.length() ? sel : current.length());
                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String values of EditText variables
                String stringEditName = editName.getText().toString();
                String stringEditItem = editName.getText().toString();
                String stringEditItemQuant = editName.getText().toString();
                String stringEditItemEa = editName.getText().toString();
                String stringEditAmount = editName.getText().toString();
                String stringEditDueDate = editName.getText().toString();


                if(stringEditName.isEmpty() || stringEditItem.isEmpty() || stringEditItemQuant.isEmpty()
                        || stringEditItemEa.isEmpty() || stringEditAmount.isEmpty() || stringEditDueDate.isEmpty()) {
                    Toast.makeText(CreateNewInvoice.this, "Please ensure to fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    String formattedAmount = formatNumberCurrency(editAmount.getText().toString().substring(2));


                    addNewInvoice(editName.getText().toString(), editItem.getText().toString(),
                            editItemQuant.getText().toString(), editItemEa.getText().toString(),
                            "$ " + formattedAmount, editDueDate.getText().toString());

                }
            }
        });
    }
    private static String formatNumberCurrency(String number) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(number));
    }


    private void addNewInvoice(String clientName, String item, String itemQuant, String itemEa, String amount, String dueDate) {
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this.getApplicationContext());

        Invoice invoice = new Invoice(clientName, item, itemQuant,itemEa,amount,dueDate, "Unpaid");

        databaseHelper.invoiceDao().addInvoice(
                invoice
        );

        //Method which uses JAXB to convert object to XML



        ArrayList<Invoice> arrInvoices = (ArrayList<Invoice>) databaseHelper.invoiceDao()
                .getAllInvoice();

        for(int i = 0; i<arrInvoices.size(); i++) {
            Log.d(TAG, "Client Name: " + arrInvoices.get(i).getClientName() +
                    " Amount: " + arrInvoices.get(i).getAmount());
        }

        finish();
    }
}