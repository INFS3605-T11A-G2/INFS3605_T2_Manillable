package com.example.manillable;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class InvoiceViewModel extends AndroidViewModel {

        private InvoiceRepository mRepository;

        private final List<Invoice> mAllInvoices;

        public InvoiceViewModel (Application application) {
            super(application);
            mRepository = new InvoiceRepository(application);
            mAllInvoices = mRepository.getAllInvoices();
        }

        List<Invoice> getAllInvoices() {
            return mAllInvoices;
        }

        public void insert(Invoice invoice) {
            mRepository.insert(invoice);
        }
    }

