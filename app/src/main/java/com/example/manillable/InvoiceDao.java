package com.example.manillable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InvoiceDao {

    @Query("select * from invoice_table")
    List<Invoice> getAllInvoice();

    @Insert
    void addInvoice(Invoice invoice);

    @Update
    void updateInvoice(Invoice invoice);

    @Delete
    void deleteInvoice(Invoice invoice);



}
