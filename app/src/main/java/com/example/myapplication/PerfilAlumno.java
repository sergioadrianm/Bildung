package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.myapplication.AccesoAlumnos.contrasena;
import static com.example.myapplication.MainActivity.id_alumno;

public class PerfilAlumno extends AppCompatActivity {
EditText a;
String id_alum,contrasena_alum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_alumno);
        a= findViewById(R.id.contrasenalumnos);
        Intent inten = getIntent();
        id_alum = inten.getStringExtra(id_alumno);

        Intent inten2 = getIntent();
        contrasena_alum = inten2.getStringExtra(contrasena);

        a.setText(contrasena);
    }

    public void salircambiopwd(View view) {
        Intent an = new Intent(this, AccesoAlumnos.class);
        startActivity(an);
    }

    public void actualizarpwd(View view) {
        actualizarcontrasena();
        Intent an = new Intent(this, AccesoAlumnos.class);
        startActivity(an);
    }

    private void actualizarcontrasena() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        String contraseña_alumno = a.getText().toString();


        if (!contraseña_alumno.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("CONTRASENA_ALUMNO", contraseña_alumno);


            basededatos.update("ALUMNO", registro, "ALUMNO_ID='" + id_alumno + "'", null);
            basededatos.close();

            Toast.makeText(this, "La contraseña se ha modificado correctamente", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(this, "Debes indicar una contraseña nueva", Toast.LENGTH_SHORT).show();
        }

    }
}
