package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
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

public class CursosDocente extends AppCompatActivity {
    ActionBar actionbar;
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Curso> listaCursos;
    String id_docent;
   public static int id_curso;
   public static  String cursi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cursos_docente);
        actionbar = getSupportActionBar();
        actionbar.setTitle("CURSOS");
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
              cursi = String.valueOf(id_curso);
                Intent pa4 = new Intent(CursosDocente.this, Alumnos.class);
                pa4.putExtra(cursi,cursi);
                Intent pa5 = new Intent(CursosDocente.this, EdicionAlumnos.class);
                pa5.putExtra(cursi,cursi);
                Intent myIntent = new Intent(getApplicationContext(), Alumnos.class);
                startActivity(myIntent);
            }
        });

    }

    private void consultarListaPersonas() {
        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase  basededatos = admin.getWritableDatabase();
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


    public void volverdocente(View view) {
        Intent ci = new Intent(this, Docentes.class);
        startActivity(ci);
    }

    public void anadir_curso(View view) {
        Intent ci = new Intent(this, EdicionCursos.class);
        startActivity(ci);
    }

    public void eliminar_curso(View view) {
        Intent ci = new Intent(this, EliminarCursos.class);
        startActivity(ci);
    }

    public void actualizar_curso(View view) {
        Intent ci = new Intent(this, ModificarCursos.class);
        startActivity(ci);
    }

}





