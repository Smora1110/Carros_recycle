package com.example.carros_recycle.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.carros_recycle.model.entities.Carros;

@Database(entities = {Carros.class},version = 1)
public abstract class Basesdb extends RoomDatabase {
    public  abstract CarrosDAO carrosDAO();

    private static Basesdb instancia = null;

    public static Basesdb obtenerInstacia(Context micontexto){
        if(instancia==null){
            instancia = Room.databaseBuilder(micontexto,Basesdb.class,"Carros_recycle.db").allowMainThreadQueries().build();
        }

        return instancia;
    }


}
