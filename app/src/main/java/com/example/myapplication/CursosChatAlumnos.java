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

import com.example.componentead.AdminSQLiteOpenHelper;
import com.example.pojos.Alumno;

import java.util.ArrayList;

import static com.example.myapplication.MainActivity.id_alumno;

public class CursosChatAlumnos extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Alumno> listaAlumnos;
    public static String primer_apellido, nombre_alumno, nombre_cursoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cursos_chat_alumnos);
        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas10);
        sacarprimerapellido();
        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewPersonas.setAdapter(adaptador);
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nombre_cursoo = listaAlumnos.get(position).getNombre_curso();
                Intent pa15 = new Intent(CursosChatAlumnos.this, MensajesAlumnos.class);
                pa15.putExtra(nombre_cursoo, nombre_cursoo);
                Intent pa16 = new Intent(CursosChatAlumnos.this, MensajesAlumnos.class);
                pa16.putExtra(nombre_alumno, nombre_alumno);
                Intent ci = new Intent(CursosChatAlumnos.this, MensajesAlumnos.class);
                startActivity(ci);

            }
        });
    }


    private void sacarprimerapellido() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select NOMBRE_ALUMNO,PRIMER_APELLIDO from ALUMNO where ALUMNO_ID='" + id_alumno + "'", null);

        if (fila.moveToFirst()) {
            do {
                nombre_alumno = fila.getString(fila.getColumnIndex("NOMBRE_ALUMNO"));
                primer_apellido = fila.getString(fila.getColumnIndex("PRIMER_APELLIDO"));
                basededatos.close();


            } while (fila.moveToNext());
        } else {
            basededatos.close();
        }
    }

    public void volveraccesoalumnos(View view) {
        Intent intent = new Intent(CursosChatAlumnos.this, AccesoAlumnos.class);
        startActivity(intent);
    }

    private void consultarListaPersonas() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        Alumno alumno;
        listaAlumnos = new ArrayList<Alumno>();
        listaInformacion = new ArrayList<>();
        Cursor fila = basededatos.rawQuery("select * from ALUMNO where NOMBRE_ALUMNO='" + nombre_alumno + "' and PRIMER_APELLIDO = '" + primer_apellido + "'", null);
        while (fila.moveToNext()) {
            alumno = new Alumno();
            alumno.setAlumno_id(fila.getInt(0));
            alumno.setImagen(fila.getBlob(1));
            alumno.setNombre_alumno(fila.getString(2));
            alumno.setPrimer_apellido(fila.getString(3));
            alumno.setSegundo_apellido(fila.getString(4));
            alumno.setContraseña(fila.getString(5));
            alumno.setEmail(fila.getString(6));
            alumno.setObservaciones(fila.getString(7));
            alumno.setRol_id(fila.getInt(8));
            alumno.setCurso_id(fila.getInt(9));
            alumno.setNombre_curso(fila.getString(10));
            alumno.setDocente_id(fila.getInt(11));
            listaAlumnos.add(alumno);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaAlumnos.size(); i++) {
            listaInformacion.add(listaAlumnos.get(i).getNombre_curso());
        }
    }
}
