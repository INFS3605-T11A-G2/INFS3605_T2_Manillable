package com.example.manillable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class InvoiceDetail extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "intent_message";
    private static final String TAG = "InvoiceDetail";
    private TextView mClientName, mEdit, mDone, mAmount, mPaid;
    private Button mResend, mMarkPaid, mPreviewPDF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_detail);

        mClientName = findViewById(R.id.txt_clientName);
        mEdit = findViewById(R.id.txt_Edit);
        mDone = findViewById(R.id.txt_Done);
        mAmount = findViewById(R.id.txt_amount_detail);
        mPaid = findViewById(R.id.txt_paid);
        mResend = findViewById(R.id.btn_resend);
        mMarkPaid = findViewById(R.id.btn_markPaid);
        mPreviewPDF = findViewById(R.id.btn_previewPdf);
        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_MESSAGE)) {
            String invoiceSymbol = getIntent().getStringExtra(INTENT_MESSAGE);
            Log.d(TAG, "INTENT_MESSAGE = " + invoiceSymbol);

            InvoiceDatabaseHelper invoiceDatabaseHelper = InvoiceDatabaseHelper.getDB(InvoiceDetail.this);

            Invoice invoice = invoiceDatabaseHelper.invoiceDao().getInvoice(invoiceSymbol);

            Log.d(TAG, "Client Name: " + invoice.getClientName());

            if (invoice != null) {
                mClientName.setText(invoice.getClientName());
                mAmount.setText(invoice.getAmount());
                mPaid.setText(invoice.getPaid());
            }

        }

    }
}