package com.example.manillable;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InvoiceDao {

    @Query("select * from invoice_table")
    List<Invoice> getAllInvoice();


    @Insert
    void addTx(Invoice invoice);



}
