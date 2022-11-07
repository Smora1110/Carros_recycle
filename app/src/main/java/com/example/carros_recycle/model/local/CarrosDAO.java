package com.example.carros_recycle.model.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.carros_recycle.model.entities.Carros;

import java.util.List;

@Dao
public interface CarrosDAO {
    @Query("select * from carros ")
    List<Carros> obtenertodo();
    @Insert
    void insertarElemento(Carros miniCarro);
    @Update
    void editar(Carros miniCarro);

    @Delete
    void eliminar(Carros miniCarro);

    @Query("select * from carros where identificador=:parametro")
    Carros obtenerPorIdentificador(int parametro);
}
