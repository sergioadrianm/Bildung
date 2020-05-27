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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.myapplication.MainActivity.id;

public class CursosChat extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Curso> listaCursos;
    public static String nombre;
  public static  String nombre_curso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cursos_chat);
        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas4);
        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);
        sacarnombre();
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nombre_curso =  listaCursos.get(position).getNombre_curso();
                Intent ci = new Intent(CursosChat.this, MensajesDocente.class);
                startActivity(ci);

            }
        });

    }

    private void sacarnombre() {
        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase  basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select NOMBRE from DOCENTE where DOCENTE_ID='" + id + "'", null);

        if(fila.moveToFirst() ) {
            do {
                 nombre = fila.getString(fila.getColumnIndex("NOMBRE"));
                basededatos.close();


            }while(fila.moveToNext());
        }else{
            basededatos.close();
        }
    }

    public void volverdocente(View view) {
        Intent intent = new Intent(CursosChat.this, Docentes.class);
        startActivity(intent);
    }
    private void consultarListaPersonas() {
        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        Curso curso;
        listaCursos= new ArrayList<Curso>();
        listaInformacion = new ArrayList<>();
        Cursor fila = basededatos.rawQuery("select * from CURSO where DOCENTE_ID = '" + id + "'" , null);
        while (fila.moveToNext()){
            curso=new Curso();
            curso.setCurso_id(fila.getInt(0));
            curso.setNombre_curso(fila.getString(1));
            curso.setDescripcion(fila.getString(2));
            curso.setCentro_educativo_id(fila.getInt(3));
            curso.setDocente_id(fila.getInt(4));
            listaCursos.add(curso);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion= new ArrayList<String>();

        for(int i=0; i<listaCursos.size();i++){
            listaInformacion.add(listaCursos.get(i).getNombre_curso());
        }
    }


}
