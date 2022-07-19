package com.example.manillable;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Invoice.class}, version = 1, exportSchema = false)
public abstract class InvoiceRoomDatabase extends RoomDatabase {
    public abstract InvoiceDao invoiceDao();

    private static volatile InvoiceRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static InvoiceRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InvoiceRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InvoiceRoomDatabase.class, "invoice_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                InvoiceDao dao = INSTANCE.invoiceDao();
                dao.deleteAll();

                Invoice invoice = new Invoice("1", "user1", "John",
                        "Smith", "Invoice Item", "Item to be invoiced",
                        "36.05", "1", "36.05", "19/07/2022");
                dao.insert(invoice);
            });
        }
    };


}
