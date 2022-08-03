package com.example.manillable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class InvoiceDetail extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "intent_message";
    private static final String TAG = "InvoiceDetail";
    private TextView mClientName, mEdit, mDone, mAmount, mPaid;
    private ImageView mImagePdf;
    private Button mResend, mMarkPaid, mPreviewPDF;
    private ProgressBar progressBar;
    private static int PROGRESS_TIME_OUT = 3000;


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
        mImagePdf = findViewById(R.id.iv_pdfImage);
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
            mPreviewPDF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPreviewPDF.setVisibility(View.INVISIBLE);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    Picasso.get()
                                            .load(R.drawable.generatedinvoice)
                                            .resize(270,330)
                                            .into(mImagePdf);

                                }
                            });
                        }
                    }, PROGRESS_TIME_OUT);
                }
            });

            mDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            mMarkPaid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    invoice.setPaid("Paid");
                    invoiceDatabaseHelper.invoiceDao().updateInvoice(invoice);
                    finish();
                }
            });

            mResend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String addresses = invoice.getClientName() + "@gmail.com";
                    String subject = invoice.getClientName() + " Invoice Overdue";
                    Uri myURI = Uri.parse("android.resource://com.example.manillable/" + R.drawable.generatedinvoice2);
                    composeEmail(addresses, subject, invoice, myURI);
                }
            });

            mImagePdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setType("image/*");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }
    }

    public void composeEmail(String addresses, String subject, Invoice invoice, Uri myURI) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, "Hi " + invoice.getClientName() + ", \n"
                + "Please see the attached documents in regards to the work I completed.");
        intent.putExtra(Intent.EXTRA_STREAM, myURI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private void createPdf(String clientName, String item, String itemQuant, String itemEa, String amount, String dueDate, String paid) throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "generated_invoice.pdf");
        OutputStream outputStream = new FileOutputStream(file);


    }

}