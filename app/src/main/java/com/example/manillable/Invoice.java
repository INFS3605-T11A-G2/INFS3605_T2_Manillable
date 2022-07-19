package com.example.manillable;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice_table")
public class Invoice {

    @NonNull

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "client_name")
    private String clientName;
    @ColumnInfo(name = "amount")
    private String amount;


}
