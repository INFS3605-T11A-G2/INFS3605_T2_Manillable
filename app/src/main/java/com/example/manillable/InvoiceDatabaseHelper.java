package com.example.manillable;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Invoice.class, exportSchema = false, version = 1)
public abstract class InvoiceDatabaseHelper extends RoomDatabase {
    private static final String DB_NAME = "invoicedb";
    private static InvoiceDatabaseHelper instance;

    public static synchronized InvoiceDatabaseHelper getDB(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, InvoiceDatabaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract InvoiceDao invoiceDao();
}
