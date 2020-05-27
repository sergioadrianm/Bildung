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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.myapplication.MainActivity.id;

public class EliminarCursos extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Curso> listaCursos;
   public static String id_docent,nombre_curso;
    public static int id_curso;
    private DatabaseReference root = FirebaseDatabase.getInstance().getInstance().getReference().getRoot();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_cursos);
        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas);
        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);
        Intent inten5 = getIntent();
        id_docent = inten5.getStringExtra(id);
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_curso =  listaCursos.get(position).getCurso_id();
                nombre_curso = listaCursos.get(position).getNombre_curso();
                    eliminarcursos();

                Intent myIntent = new Intent(getApplicationContext(), CursosDocente.class);
                startActivity(myIntent);
            }
        });

    }

    private void eliminarcursos() {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
            SQLiteDatabase basededatos = admin.getWritableDatabase();

        basededatos.delete("ALUMNO","CURSO_ID ='" + id_curso + "'",null);
        basededatos.delete("CURSO","CURSO_ID ='" + id_curso + "'",null);


        root.child(nombre_curso).removeValue();
           basededatos.close();

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
    public void volvercursos(View view) {
        Intent ci = new Intent(this, CursosDocente.class);
        startActivity(ci);
    }
}
