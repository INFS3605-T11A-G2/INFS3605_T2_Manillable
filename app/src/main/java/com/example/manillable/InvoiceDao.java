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
    void addInvoice(Invoice...invoice);

    // Query and return ONE invoice's results based on the received symbol
    @Query("SELECT * FROM invoice_table WHERE id == :invoiceSymbol")
    Invoice getInvoice(String invoiceSymbol);

    // Query and return ONE invoice's results based on the received symbol
    @Query("SELECT * FROM invoice_table WHERE paid == :Unpaid")
    List<Invoice> getUnpaid(String Unpaid);

    // Query and return ONE invoice's results based on the received symbol
    @Query("SELECT * FROM invoice_table WHERE paid == :Paid")
    List<Invoice> getPaid(String Paid);

    @Update
    void updateInvoice(Invoice invoice);

    @Delete
    void deleteInvoice(Invoice invoice);



}
