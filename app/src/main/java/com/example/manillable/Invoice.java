package com.example.manillable;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "invoice_table")
public class Invoice {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "client_name")
    private String clientName;

    @ColumnInfo(name = "item_desc")
    private String item;

    @ColumnInfo(name = "item_quant")
    private String itemQuant;

    @ColumnInfo(name = "item_ea")
    private String itemEa;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "due_date")
    private String dueDate;

    @ColumnInfo(name = "paid")
    private String paid;

    public Invoice(int id, String clientName, String item, String itemQuant, String itemEa, String amount, String dueDate, String paid) {
        this.id = id;
        this.clientName = clientName;
        this.item = item;
        this.itemQuant = itemQuant;
        this.itemEa = itemEa;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paid = paid;
    }

    /**
     * I think this is just ensuring a @NonNull on the PK
     * @param clientName
     * @param amount
     */
    @Ignore
    Invoice(String clientName, String item, String itemQuant, String itemEa, String amount, String dueDate, String paid) {
        this.clientName = clientName;
        this.item = item;
        this.itemQuant = itemQuant;
        this.itemEa = itemEa;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paid = paid;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemQuant() {
        return itemQuant;
    }

    public void setItemQuant(String itemQuant) {
        this.itemQuant = itemQuant;
    }

    public String getItemEa() {
        return itemEa;
    }

    public void setItemEa(String itemEa) {
        this.itemEa = itemEa;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
