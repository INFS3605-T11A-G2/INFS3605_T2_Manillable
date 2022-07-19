package com.example.manillable;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InvoiceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Invoice invoice);

    @Query("DELETE FROM invoice_table")
    void deleteAll();

    @Query("SELECT * FROM invoice_table")
    List<Invoice> getAllInvoices();
}
