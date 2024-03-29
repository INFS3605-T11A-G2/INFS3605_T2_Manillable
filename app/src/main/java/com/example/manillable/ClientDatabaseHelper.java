package com.example.manillable;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Client.class, exportSchema = false, version = 1)
public abstract class ClientDatabaseHelper extends RoomDatabase {
    private static final String DB_NAME = "clientdb";
    private static ClientDatabaseHelper instance;

    public static synchronized ClientDatabaseHelper getDB(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ClientDatabaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract ClientDao clientDao();
}
