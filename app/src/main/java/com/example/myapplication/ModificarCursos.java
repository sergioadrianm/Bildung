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
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.myapplication.MainActivity.id;
public class ModificarCursos extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Curso> listaCursos;
   public static int id_curso;
   public static String cursi_id;
   public static String nombre_curso,descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_curso);
        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas2);
        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_curso =  listaCursos.get(position).getCurso_id();
                cursi_id = String.valueOf(id_curso);
                Intent pa4 = new Intent(ModificarCursos.this, EdicionCursos.class);
                pa4.putExtra(cursi_id, cursi_id);
                cargardatoscurso();
                Intent myIntent = new Intent(getApplicationContext(), EdicionCursos.class);
                startActivity(myIntent);

            }
        });
    }

    private void cargardatoscurso() {

        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase  basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select NOMBRE_CURSO,DESCRIPCION from CURSO where CURSO_ID='" + id_curso + "'", null);

        if(fila.moveToFirst() ) {
            do {
                nombre_curso  = fila.getString(fila.getColumnIndex("NOMBRE_CURSO"));
                descripcion  = fila.getString(fila.getColumnIndex("DESCRIPCION"));

                basededatos.close();
                Intent pa12 = new Intent(ModificarCursos.this, EdicionCursos.class);
                pa12.putExtra(nombre_curso, nombre_curso);
                Intent pa13 = new Intent(ModificarCursos.this, EdicionCursos.class);
                pa13.putExtra(descripcion, descripcion);


            }while(fila.moveToNext());
        }else{
            basededatos.close();
        }
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
    public void volveralumnos(View view) {
        Intent reg = new Intent(this, Alumnos.class);
        startActivity(reg);
    }
}
