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

import static com.example.myapplication.MainActivity.id;

public class CursosTareasAlumnos extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Curso> listaCursos;
    public static int id_c;
    public static  String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cursos_tareas_alumnos);
        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas3);
        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);
                listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        id_c =  listaCursos.get(position).getCurso_id();
                        c = String.valueOf(id_c);
                        Intent pa5 = new Intent(CursosTareasAlumnos.this, TareasDocente.class);
                        pa5.putExtra(c,c);
                        Intent myIntent = new Intent(getApplicationContext(), TareasDocente.class);
                        startActivity(myIntent);
            }
        });
    }

    public void volverdocente(View view) {
        Intent an = new Intent(this, Docentes.class);
        startActivity(an);
    }
    private void consultarListaPersonas() {
        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        Curso curso;
        listaCursos= new ArrayList<Curso>();
        listaInformacion = new ArrayList<>();
        Cursor fila = basededatos.rawQuery("select * from CURSO where DOCENTE_ID = '" + id + "'", null);
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
            listaInformacion.add(listaCursos.get(i).getNombre_curso()+ " - " + listaCursos.get(i).getDescripcion());
        }
    }


}
