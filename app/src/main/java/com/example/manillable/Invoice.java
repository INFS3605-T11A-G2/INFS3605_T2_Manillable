package com.example.manillable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice_table")
public class Invoice {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String mId;

    @ColumnInfo(name = "user_id")
    public String mUid;

    @ColumnInfo(name = "client_first_name")
    public String mClientFName;

    @ColumnInfo(name = "client_last_name")
    public String mClientLName;

    @ColumnInfo(name = "item_name")
    public String mItemName;

    @ColumnInfo(name = "item_desc")
    public String mItemDesc;

    @ColumnInfo(name = "item_rate")
    public String mItemRate;

    @ColumnInfo(name = "item_quant")
    public String mItemQuant;

    @ColumnInfo(name = "amount")
    public String mAmount;

    @ColumnInfo(name = "due_date")
    public String mDueDate;

    public Invoice(@NonNull String id, String uid, String clientFName, String clientLName,
                   String itemName, String itemDesc, String itemRate, String itemQuant,
                   String amount, String dueDate) {
        this.mId = id;
        this.mUid = uid;
        this.mClientFName = clientFName;
        this.mClientLName = clientLName;
        this.mItemName = itemName;
        this.mItemDesc = itemDesc;
        this.mItemRate = itemRate;
        this.mItemQuant = itemQuant;
        this.mAmount = amount;
        this.mDueDate = dueDate;
    }

    @NonNull
    public String getmId() {
        return mId;
    }

    public void setmId(@NonNull String mId) {
        this.mId = mId;
    }

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public String getmClientFName() {
        return mClientFName;
    }

    public void setmClientFName(String mClientFName) {
        this.mClientFName = mClientFName;
    }

    public String getmClientLName() {
        return mClientLName;
    }

    public void setmClientLName(String mClientLName) {
        this.mClientLName = mClientLName;
    }

    public String getmItemName() {
        return mItemName;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public String getmItemDesc() {
        return mItemDesc;
    }

    public void setmItemDesc(String mItemDesc) {
        this.mItemDesc = mItemDesc;
    }

    public String getmItemRate() {
        return mItemRate;
    }

    public void setmItemRate(String mItemRate) {
        this.mItemRate = mItemRate;
    }

    public String getmItemQuant() {
        return mItemQuant;
    }

    public void setmItemQuant(String mItemQuant) {
        this.mItemQuant = mItemQuant;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public String getmDueDate() {
        return mDueDate;
    }

    public void setmDueDate(String mDueDate) {
        this.mDueDate = mDueDate;
    }
}
