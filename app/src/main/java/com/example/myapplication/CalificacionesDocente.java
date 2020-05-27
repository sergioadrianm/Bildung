package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalificacionesDocente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificaciones_docente);
    }

    public void salircalificaciones(View view) {
        Intent intent = new Intent(CalificacionesDocente.this, Docentes.class);
        startActivity(intent);
    }
}
