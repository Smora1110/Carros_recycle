package com.example.carros_recycle.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carros_recycle.R;
import com.example.carros_recycle.model.entities.Carros;
import com.example.carros_recycle.model.repository.CarroRepository;
import com.google.firebase.firestore.FirebaseFirestore;

public class FormularioActivity extends AppCompatActivity {

    private static final String NOMBRE_COLECCION = "carros";
    EditText etNombre , etDescripcion , etUrlPortada;
    Button btGuardada;
    private CarroRepository micarroRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        micarroRepository = new CarroRepository(getApplicationContext());
        referenciarElementos();
    }
    private  void referenciarElementos(){
        etNombre= findViewById(R.id.nombref);
        etDescripcion= findViewById(R.id.descripcionf);

        etUrlPortada= findViewById(R.id.urlf);
        btGuardada= findViewById(R.id.botonf);


    }
    public  void clickguardar(View view){
        String nombre=etNombre.getText().toString();
        String desc=etDescripcion.getText().toString();
        String uurl=etUrlPortada.getText().toString();
        if("".equals(nombre)){
            etNombre.setError(getString(R.string.errorvalidacion));
            return;
        }

        if("".equals(desc)){
            etDescripcion.setError(getString(R.string.errorvalidacion));
            return;
        }

        if("".equals(uurl)){
            etUrlPortada.setError(getString(R.string.errorvalidacion));
            return;
        }

        Carros carro = new Carros(nombre, desc, uurl,"");
        Intent datos = new Intent();
        datos.putExtra("datos_de_carro",carro);

        setResult(RESULT_OK,datos);
        //Intent regresar = new Intent(FormularioActivity.this, MainActivity.class);
        //startActivity(regresar);

        FirebaseFirestore firebase = FirebaseFirestore.getInstance();
        firebase.collection(NOMBRE_COLECCION).add(carro);

       // micarroRepository.insertarCarro(carro);

        finish();
    }

}