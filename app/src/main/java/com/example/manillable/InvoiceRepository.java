package com.example.manillable;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class InvoiceRepository {
    private InvoiceDao mInvoiceDao;
    private List<Invoice> mAllInvoices;

    InvoiceRepository(Application application) {
        InvoiceRoomDatabase db = InvoiceRoomDatabase.getDatabase(application);
        mInvoiceDao = db.invoiceDao();
        mAllInvoices = mInvoiceDao.getAllInvoices();
    }

    List<Invoice> getAllInvoices() {
        return mAllInvoices;
    }

    void insert(Invoice invoice) {
        InvoiceRoomDatabase.databaseWriteExecutor.execute(() -> {
            mInvoiceDao.insert(invoice);
        });
    }


}
