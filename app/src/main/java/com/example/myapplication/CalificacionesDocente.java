package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adaptadorlistview.AdaptadorListViewCurso;
import com.example.componentead.AdminSQLiteOpenHelper;
import com.example.pojos.Curso;

import java.util.ArrayList;

import static com.example.myapplication.MainActivity.id;

public class CalificacionesDocente extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Curso> listaCursos;
    public static int fg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificaciones_docente);
        listViewPersonas = (ListView) findViewById(R.id.listViewCursosCalificaciones);
        consultarListaPersonas();
        AdaptadorListViewCurso a = new AdaptadorListViewCurso(this, R.layout.adaptador_list_view_curso, listaCursos);
        listViewPersonas.setAdapter(a);
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fg = listaCursos.get(position).getCurso_id();
                Intent myIntent = new Intent(getApplicationContext(), AlumnosCalificaciones.class);
                startActivity(myIntent);
            }
        });
    }

    private void consultarListaPersonas() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        Curso curso;
        listaCursos = new ArrayList<Curso>();
        listaInformacion = new ArrayList<>();
        Cursor fila = basededatos.rawQuery("select * from CURSO where DOCENTE_ID = '" + id + "'", null);
        while (fila.moveToNext()) {
            curso = new Curso();
            curso.setCurso_id(fila.getInt(0));
            curso.setImagen_curso(fila.getBlob(1));
            curso.setNombre_curso(fila.getString(2));
            curso.setDescripcion(fila.getString(3));
            curso.setCentro_educativo_id(fila.getInt(4));
            curso.setDocente_id(fila.getInt(5));
            listaCursos.add(curso);

        }

        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaCursos.size(); i++) {
            listaInformacion.add(listaCursos.get(i).getNombre_curso() + " - " + listaCursos.get(i).getDescripcion());
        }
    }

    public void salircalificaciones(View view) {
        Intent intent = new Intent(CalificacionesDocente.this, Docentes.class);
        startActivity(intent);
    }
}
