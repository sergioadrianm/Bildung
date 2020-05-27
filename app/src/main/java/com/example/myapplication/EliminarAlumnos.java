package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.myapplication.Alumnos.cursio;
import static com.example.myapplication.CursosDocente.cursi;

public class EliminarAlumnos extends AppCompatActivity {
    ListView listViewAlumnos;
    ArrayList<String> listaInformacion;
    ArrayList<Alumno> listAlumno;
    ArrayAdapter adaptador;
     public static int id_alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_alumnos);
        listViewAlumnos = (ListView) findViewById(R.id.listViewAlumnos2);
        consultarListaPersonas();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewAlumnos.setAdapter(adaptador);
        listViewAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_alumno =  listAlumno.get(position).getAlumno_id();
                eliminaralumnos();

                Intent myIntent = new Intent(getApplicationContext(), Alumnos.class);
                startActivity(myIntent);
            }
        });

    }

    private void eliminaralumnos() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();

        basededatos.delete("ALUMNO","ALUMNO_ID ='" + id_alumno + "'",null);
        basededatos.close();
    }



    public void volveralumnos(View view) {
        Intent ci = new Intent(this, Alumnos.class);
        startActivity(ci);
    }


    private void consultarListaPersonas() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        Alumno alumno;
        listAlumno = new ArrayList<Alumno>();
        listaInformacion = new ArrayList<>();
        Cursor fila = basededatos.rawQuery("select * from ALUMNO where CURSO_ID = '" + cursi + "'", null);
        while (fila.moveToNext()) {
            alumno = new Alumno();
            alumno.setAlumno_id(fila.getInt(0));
            alumno.setNombre_alumno(fila.getString(1));
            alumno.setPrimer_apellido(fila.getString(2));
            alumno.setSegundo_apellido(fila.getString(3));
            alumno.setContraseña(fila.getString(4));
            alumno.setEmail(fila.getString(5));
            alumno.setObservaciones(fila.getString(6));
            alumno.setRol_id(fila.getInt(7));
            alumno.setCurso_id(fila.getInt(8));
            alumno.setDocente_id(fila.getInt(9));


            listAlumno.add(alumno);
        }
        obtenerLista();
    }
    private void obtenerLista() {
        listaInformacion= new ArrayList<String>();

        for(int i=0; i<listAlumno.size();i++){
            listaInformacion.add(listAlumno.get(i).getNombre_alumno()+ "  " + listAlumno.get(i).getPrimer_apellido()+
                    "  " + listAlumno.get(i).getSegundo_apellido()+ " - " + listAlumno.get(i).getObservaciones());
        }
    }

}
