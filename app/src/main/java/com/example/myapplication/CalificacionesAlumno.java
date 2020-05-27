package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalificacionesAlumno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificaciones_alumno);
    }

    public void salircalificacionesa(View view) {
        Intent intent = new Intent(CalificacionesAlumno.this, AccesoAlumnos.class);
        startActivity(intent);
    }
}
