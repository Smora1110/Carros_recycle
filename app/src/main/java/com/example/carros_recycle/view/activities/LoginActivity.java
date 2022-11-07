package com.example.carros_recycle.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carros_recycle.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPass;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("Logueado", false)){
            finish();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }
        referenciar();
    }

    private void referenciar(){
        etEmail = findViewById(R.id.et_email);
        etPass = findViewById(R.id.et_pass);
    }
    public void clickLogin(View view){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if (email.equals("Jonathan")&&pass.equals("123456")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            //finish();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Logueado", true);
            editor.apply();



        }else {
            Toast.makeText(this, R.string.texterror, Toast.LENGTH_SHORT);
        }
    }
}