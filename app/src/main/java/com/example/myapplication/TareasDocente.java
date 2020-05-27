package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import static com.example.myapplication.MainActivity.id;
public class TareasDocente extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Tarea> listaTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas_docente);
        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas90);
        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);
    }

    public void salirtareas(View view) {
        Intent intent = new Intent(TareasDocente.this,Docentes.class);
        startActivity(intent);
    }

    public void pasarbn(View view) {
        Intent intent = new Intent(TareasDocente.this,EdicionTareas.class);
        startActivity(intent);
    }
    private void consultarListaPersonas() {
        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        Tarea tarea;
        listaTareas= new ArrayList<Tarea>();
        listaInformacion = new ArrayList<>();
        Cursor fila = basededatos.rawQuery("select * from TAREA where DOCENTE_ID = '" + id + "'", null);
        while (fila.moveToNext()){
            tarea=new Tarea();
            tarea.setTarea_id(fila.getInt(0));
            tarea.setNombre_tarea(fila.getString(1));
            tarea.setInstrucciones(fila.getString(2));
            tarea.setFecha_limite(fila.getString(3));
            tarea.setRubrica_id(fila.getInt(4));
            tarea.setCurso_id(fila.getInt(5));
            tarea.setDocente_id(fila.getInt(6));
            listaTareas.add(tarea);

        }

        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion= new ArrayList<String>();

        for(int i=0; i<listaTareas.size();i++){
            listaInformacion.add(listaTareas.get(i).getNombre_tarea()+ " - " + listaTareas.get(i).getInstrucciones());
        }
    }
}
