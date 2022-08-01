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

            mResend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent = new Intent(Intent.ACTION_VIEW)
                            .setType("plain/text")
                            .setData(Uri.parse("test@gmail.com"))
                            .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
                            .putExtra(Intent.EXTRA_EMAIL, new String[]{"test@gmail.com"})
                            .putExtra(Intent.EXTRA_SUBJECT, invoice.getClientName() + " Invoice Overdue")
                            .putExtra(Intent.EXTRA_TEXT, "Hi " + invoice.getClientName() + ", \n"
                                + "Please see the attached documents in regards to the work I completed.");
                    startActivity(intent);

                }
            });
        }
    }

    private void createPdf(String clientName, String item, String itemQuant, String itemEa, String amount, String dueDate, String paid) throws FileNotFoundException {
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "generated_invoice.pdf");
        OutputStream outputStream = new FileOutputStream(file);


    }

}