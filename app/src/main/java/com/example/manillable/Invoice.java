package com.example.manillable;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice_table")
public class Invoice {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "client_name")
    private String clientName;

    @ColumnInfo(name = "amount")
    private String amount;

    Invoice(int id, String clientName, String amount) {
        this.id = id;
        this.clientName = clientName;
        this.amount = amount;
    }

    /**
     * I think this is just ensuring a @NonNull on the PK
     * @param clientName
     * @param amount
     */
    @Ignore
    Invoice(String clientName, String amount) {
        this.clientName = clientName;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
