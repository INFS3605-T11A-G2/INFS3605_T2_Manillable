package com.example.manillable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientDao {
    @Query("select * from client_table")
    List<Client> getAllClient();

    @Insert
    void addClient(Client...client);

    @Update
    void updateClient(Client client);

    @Delete
    void deleteClient(Client client);
}
