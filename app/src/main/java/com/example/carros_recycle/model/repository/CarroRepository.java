package com.example.carros_recycle.model.repository;

import android.content.Context;

import com.example.carros_recycle.model.entities.Carros;
import com.example.carros_recycle.model.local.Basesdb;
import com.example.carros_recycle.model.local.CarrosDAO;

import java.util.List;

public class CarroRepository {

    CarrosDAO carrosDAO;

    public CarroRepository(Context micontext){
        Basesdb miBaseDatos = Basesdb.obtenerInstacia(micontext);
        this.carrosDAO = miBaseDatos.carrosDAO();
    }

    public List<Carros> obtenerTodosCarro(){
        return carrosDAO.obtenertodo();
    }

    public void insertarCarro(Carros micarro){
        carrosDAO.insertarElemento(micarro);
    }
    public void actualizarCarro(Carros micarro){
        carrosDAO.editar(micarro);
    }
    public void eliminarCarro(Carros micarro){
        carrosDAO.eliminar(micarro);
    }
    public Carros consultarId(int id){
        return carrosDAO.obtenerPorIdentificador(id);
    }

}

