package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import static com.example.myapplication.MainActivity.id_alumno;

public class AccesoAlumnos extends AppCompatActivity {
EditText a;
public static String id_alum,nombre_alum,contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceso_alumnos);
        a= findViewById(R.id.nomalumno);
        Intent inten = getIntent();
        id_alum = inten.getStringExtra(id_alumno);
        sacarnombrealumno();
        indicarpwd();
        a.setText(nombre_alum);
        FloatingActionButton fab6 = (FloatingActionButton) findViewById(R.id.fb6);
        fab6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccesoAlumnos.this, MainActivity.class);
                startActivity(intent);

            }
        });

        FloatingActionButton fab7 = (FloatingActionButton) findViewById(R.id.fb7);
        fab7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent pa = new Intent(AccesoAlumnos.this, PerfilAlumno.class);
                pa.putExtra(contrasena, contrasena);
                Intent intent = new Intent(AccesoAlumnos.this, PerfilAlumno.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fab5 = (FloatingActionButton) findViewById(R.id.fb5);
        fab5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccesoAlumnos.this, CursosChatAlumnos.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fab4 = (FloatingActionButton) findViewById(R.id.fb4);
        fab4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccesoAlumnos.this, CalificacionesAlumno.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fb2);
        fab2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccesoAlumnos.this, TareasAlumnos.class);
                startActivity(intent);

            }
        });
    }
    private void sacarnombrealumno() {

        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select NOMBRE_ALUMNO from ALUMNO where ALUMNO_ID='" + id_alumno + "'", null);

        if(fila.moveToFirst() ) {
            do {
                nombre_alum  = fila.getString(fila.getColumnIndex("NOMBRE_ALUMNO"));
                basededatos.close();


            }while(fila.moveToNext());
        }else{
            basededatos.close();
        }
    }
    private void indicarpwd(){
        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select CONTRASENA_ALUMNO from ALUMNO where ALUMNO_ID='" + id_alumno + "'", null);

        if(fila.moveToFirst() ) {
            do {
                contrasena  = fila.getString(fila.getColumnIndex("CONTRASENA_ALUMNO"));
                basededatos.close();


            }while(fila.moveToNext());
        }else{
            basededatos.close();
        }
    }

}


