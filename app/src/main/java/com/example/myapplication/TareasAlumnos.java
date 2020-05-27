package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TareasAlumnos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas_alumnos);
    }

    public void salirtareasa(View view) {
        Intent intent = new Intent(TareasAlumnos.this, AccesoAlumnos.class);
        startActivity(intent);
    }
}
