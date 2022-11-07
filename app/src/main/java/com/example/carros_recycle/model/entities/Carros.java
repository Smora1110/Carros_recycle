package com.example.carros_recycle.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

@Entity

public class Carros implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int identificador;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "URLimg")
    private String URLimg;
    @ColumnInfo(name = "URL")
    private String    URL;

    //CONSTRUCTOR
    public Carros(String nombre, String descripcion, String URLimg, String URL) {
        this.identificador=0;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.URLimg = URLimg;
        this.URL = URL;
    }

    public Carros() {
        this.identificador = 0;
    }
    //public Carros(String nombre, String desc, String uurl) {

   // }

    //GETTERS AND SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @PropertyName("url_imagen")
    public String getURLimg() {
        return URLimg;
    }
    @PropertyName("url_imagen")
    public void setURLimg(String URLimg) {
        this.URLimg = URLimg;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Exclude
    public int getIdentificador() {
        return identificador;
    }
    @Exclude
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
}
