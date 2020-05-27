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
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.myapplication.CursosDocente.cursi;


public class Alumnos extends AppCompatActivity {
    public static String nomalumno,primerape,segundoape,pwd,emailalumno,obser;
    ActionBar actionbar;
ListView listViewAlumnos;
    ArrayList<String> listaInformacion;
    ArrayList<Alumno> listAlumno;
    String curso_id;
    ArrayAdapter adaptador;
    public static int id_alumni;
    public static  String cursio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alumnos);
        actionbar = getSupportActionBar();
        actionbar.setTitle("ALUMNOS");
        listViewAlumnos = (ListView) findViewById(R.id.listViewAlumnos);
        consultarListaPersonas();
          adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
          listViewAlumnos.setAdapter(adaptador);
        Intent inten6 = getIntent();
        curso_id = inten6.getStringExtra(cursi);
        Intent pa5 = new Intent(Alumnos.this, EdicionAlumnos.class);
        pa5.putExtra(cursi,cursi);
        listViewAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_alumni=  listAlumno.get(position).getAlumno_id();
                cursio = String.valueOf(id_alumni);
                cargardatosalumnos();

                Intent myIntent = new Intent(getApplicationContext(), DatosAlumno.class);
                startActivity(myIntent);
            }
        });
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
            alumno.setNombre_curso(fila.getString(9));
            alumno.setDocente_id(fila.getInt(10));
            listAlumno.add(alumno);
        }
        obtenerLista();
    }
    private void cargardatosalumnos(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select NOMBRE_ALUMNO,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CONTRASENA_ALUMNO,EMAIL_ALUMNO,OBSERVACIONES from ALUMNO where ALUMNO_ID='" + id_alumni + "'", null);

        if (fila.moveToFirst() ) {
            do {
                nomalumno = fila.getString(fila.getColumnIndex("NOMBRE_ALUMNO"));
                primerape = fila.getString(fila.getColumnIndex("PRIMER_APELLIDO"));
                segundoape = fila.getString(fila.getColumnIndex("SEGUNDO_APELLIDO"));
                pwd = fila.getString(fila.getColumnIndex("CONTRASENA_ALUMNO"));
                emailalumno = fila.getString(fila.getColumnIndex("EMAIL_ALUMNO"));
                obser = fila.getString(fila.getColumnIndex("OBSERVACIONES"));
                basededatos.close();

                Intent pa = new Intent(Alumnos.this, DatosAlumno.class);
                pa.putExtra(nomalumno,nomalumno);
                Intent pa1 = new Intent(Alumnos.this, DatosAlumno.class);
                pa1.putExtra(primerape,primerape);
                Intent pa2 = new Intent(Alumnos.this, DatosAlumno.class);
                pa2.putExtra(segundoape,segundoape);
                Intent pa3 = new Intent(Alumnos.this, DatosAlumno.class);
                pa3.putExtra(pwd,pwd);
                Intent pa4 = new Intent(Alumnos.this, DatosAlumno.class);
                pa4.putExtra(emailalumno,emailalumno);
                Intent pa5 = new Intent(Alumnos.this, DatosAlumno.class);
                pa5.putExtra(obser,obser);
            }while(fila.moveToNext());
        }
    }

    private void obtenerLista() {
        listaInformacion= new ArrayList<String>();

        for(int i=0; i<listAlumno.size();i++){
            listaInformacion.add(listAlumno.get(i).getNombre_alumno()+ "  " + listAlumno.get(i).getPrimer_apellido()+
                    "  " + listAlumno.get(i).getSegundo_apellido()+ " - " + listAlumno.get(i).getObservaciones());
        }
    }


    public void salircurso(View view) {
        Intent an = new Intent(this, CursosDocente.class);
        startActivity(an);
    }

    public void anadir_alumnos(View view) {
        Intent an = new Intent(this, EdicionAlumnos.class);
        startActivity(an);
    }

    public void eliminar_alumnos(View view) {
        Intent an = new Intent(this, EliminarAlumnos.class);
        startActivity(an);
    }
}

