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

import static com.example.myapplication.CentroEducativo.id_centro;
import static com.example.myapplication.CursosDocente.cursi;
import static com.example.myapplication.Docentes.corr;
import static com.example.myapplication.MainActivity.id;

public class EdicionAlumnos extends AppCompatActivity {
    EditText nombre_alumno,primer_ape,segundo_ape,contraseña_alumno,email,observaciones;
    int curso_alumno;
    String docente_id,nombre_cursi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_alumnos);
        nombre_alumno = findViewById(R.id.nombre_alumno);
        primer_ape = findViewById(R.id.primer_apellido_alumno);
        segundo_ape = findViewById(R.id.segundo_apellido_alumno);
        contraseña_alumno = findViewById(R.id.contraseña_alumno);
        email = findViewById(R.id.email_alumno);
        observaciones = findViewById(R.id.observaciones_alumno);
        Intent inten5 = getIntent();
        curso_alumno = inten5.getIntExtra(cursi,0);
        Intent inten6 = getIntent();
        docente_id = inten6.getStringExtra(id);
        sacarnombrecurso();
    }

    private void sacarnombrecurso() {
        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase  basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select NOMBRE_CURSO from CURSO where CURSO_ID='" + cursi + "'", null);

        if(fila.moveToFirst() ) {
            do {
                nombre_cursi  = fila.getString(fila.getColumnIndex("NOMBRE_CURSO"));
                basededatos.close();



            }while(fila.moveToNext());
        }else{
            basededatos.close();
        }
    }

    public void anadir_alumnos(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        String nombre = nombre_alumno.getText().toString();
        String primer_apellido = primer_ape.getText().toString();
        String segundo_apellido = segundo_ape.getText().toString();
        String contrasena_alumno= contraseña_alumno.getText().toString();
        String emails = email.getText().toString();
        String observacio = observaciones.getText().toString();
        Integer rol=2;

        if (!nombre.isEmpty() && !primer_apellido.isEmpty() && !contrasena_alumno.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("NOMBRE_ALUMNO", nombre);
            registro.put("PRIMER_APELLIDO",primer_apellido);
            registro.put("SEGUNDO_APELLIDO", segundo_apellido);
            registro.put("CONTRASENA_ALUMNO",contrasena_alumno);
            registro.put("EMAIL_ALUMNO",emails);
            registro.put("OBSERVACIONES",observacio);
            registro.put("ROL_ID",rol);
            registro.put("CURSO_ID", cursi);
            registro.put("NOMBRE_CURSO", nombre_cursi);
            registro.put("DOCENTE_ID", id);


            basededatos.insert("ALUMNO", null, registro);

            basededatos.close();

            Toast.makeText(this, "Alumno Insertado", Toast.LENGTH_SHORT).show();
            Intent an = new Intent(this, Alumnos.class);
            startActivity(an);

        }
        else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void saliredicionalumnos(View view) {
        Intent an = new Intent(this, Alumnos.class);
        startActivity(an);
    }
}
