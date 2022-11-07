package com.example.carros_recycle.view.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.carros_recycle.R;
import com.example.carros_recycle.model.local.Basesdb;
import com.example.carros_recycle.model.entities.Carros;
import com.example.carros_recycle.model.local.CarrosDAO;
import com.example.carros_recycle.model.repository.CarroRepository;
import com.example.carros_recycle.view.adapters.AdaptadorPersonalizado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String NOMBRE_COLECCION = "carros";
    private Button btn_agrgar;
    private RecyclerView RvPrincipal;
    private ArrayList<Carros> ListaCarros;
    private AdaptadorPersonalizado adaptador;
    private CarroRepository micarroRepository;

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.it_id:
                SharedPreferences sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("Logueado", false);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        micarroRepository = new CarroRepository(getApplicationContext());
        ListaCarros = new ArrayList<>();
        cargarDatosBasesDatos();

        btn_agrgar=findViewById(R.id.button);
         adaptador = new AdaptadorPersonalizado(ListaCarros);

        adaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Carros carro, int posicion) {
                Intent intent = new Intent(MainActivity.this, ShowWebViewActivity.class);
                intent.putExtra("url", carro.getURL());
                startActivity(intent);
              // Toast.makeText(MainActivity.this, "Click ItemBBB"+ carro.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

        RvPrincipal = findViewById(R.id.rv_carro);
        RvPrincipal.setLayoutManager(new LinearLayoutManager(this));

        RvPrincipal.setAdapter(adaptador);



    }
    private void cargarDatosBasesDatos(){

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(NOMBRE_COLECCION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    ListaCarros.clear();
                    for (DocumentSnapshot document : task.getResult()){
                        Carros micarro = document.toObject(Carros.class);
                        ListaCarros.add(micarro);
                    }

                    adaptador.setListadoDatos(ListaCarros);

                }else{
                    Log.e("error",task.getException().getMessage());
                }
            }
        });

        /*Basesdb miBaseDatos = Basesdb.obtenerInstacia(this);
        CarrosDAO carrosDAO = miBaseDatos.carrosDAO();
        ListaCarros = (ArrayList<Carros>) carrosDAO.obtenertodo();*/

        /*ListaCarros = (ArrayList<Carros>) micarroRepository.obtenerTodosCarro();*/

        /*if(ListaCarros.isEmpty()){
            creoPorDefecto();
        }*/
    }

    private void creoPorDefecto() {


        Carros carro1 = new Carros("ONIX", "Marca: Chevrolet\nHP: 115\nPrecio: 64M $COP","https://th.bing.com/th/id/R.795d673fb6762bddd99bff90ed89fe7f?rik=kjznKxR2R%2fxWbQ&pid=ImgRaw&r=0", "https://www.chevrolet.com.co/carros/onix-turbo-sedan");
        Carros carro2 = new Carros("Twingo", "Marca: Renault\nHP: 90\nPrecio: 15M $COP","https://th.bing.com/th/id/OIP.tIsD5HHWo7dOU0jMUYrSHAHaE6?pid=ImgDet&rs=1", "https://www.autobild.es/coches/renault/twingo");
        Carros carro3 = new Carros("MX5", "Marca: Mazda\nHP: 190\nPrecio: 128M $COP","https://th.bing.com/th/id/R.66c496a32d1942451ab8d4fe29f9d626?rik=ZrIjrvY4H4Lk8w&pid=ImgRaw&r=0", "https://www.mazda.com.co/vehiculos/mazda-mx-5/");
        Carros carro4 = new Carros("WRX", "Marca: Subaru\nHP: 268\nPrecio: 168M $COP","https://st.motortrendenespanol.com/uploads/sites/5/2017/07/2018-Subaru-WRX-front-three-quarter-in-motion-03.jpg", "https://www.subarucolombia.com/wrx-sedan");
        Carros carro5 = new Carros("Raptor", "Marca: Ford\nHP: 450\nPrecio: 200M $COP","https://3.bp.blogspot.com/-jq74edoKJb4/V7A0YONSKUI/AAAAAAAAAw8/6YrYiSY0jSkj4QVjlaDf2D87RTcRrnIewCLcB/s1600/1.jpg", "https://www.ford.com.co/pick-ups/ranger-raptor/");

        micarroRepository.insertarCarro(carro1);
        micarroRepository.insertarCarro(carro2);
        micarroRepository.insertarCarro(carro3);
        micarroRepository.insertarCarro(carro4);
        micarroRepository.insertarCarro(carro5);


        ListaCarros = (ArrayList<Carros>) micarroRepository.obtenerTodosCarro();



    }
    public  void onClickFormulario(View view){
        Intent irformulario = new Intent(MainActivity.this , FormularioActivity.class);
        //startActivity(irformulario);
        irFFormulario.launch(irformulario);
    }

    ActivityResultLauncher<Intent> irFFormulario = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    Carros carroatrapada = (Carros) result.getData().getSerializableExtra("datos_de_carro");
                    ListaCarros.add(carroatrapada) ;

                    adaptador.setListadoDatos(ListaCarros);


                }

        }
    });
}