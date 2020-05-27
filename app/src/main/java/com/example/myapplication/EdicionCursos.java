package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.myapplication.CentroEducativo.id_centro;
import static com.example.myapplication.MainActivity.id;
import static com.example.myapplication.ModificarCursos.cursi_id;
import static com.example.myapplication.ModificarCursos.descripcion;
import static com.example.myapplication.ModificarCursos.nombre_curso;


public class EdicionCursos extends AppCompatActivity {
    EditText a, b;

    public static String docente_curso, centro_educativo;
    public String nombre_cursi = null;
    public String descripci = null;
    public String curso_id;
    private DatabaseReference root = FirebaseDatabase.getInstance().getInstance().getReference().getRoot();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_cursos);
        a = (EditText) findViewById(R.id.nombre_curso);
        b = (EditText) findViewById(R.id.descripcion_curso);
        Intent inten4 = getIntent();
        docente_curso = inten4.getStringExtra(id);
        Intent inten5 = getIntent();
        centro_educativo = inten5.getStringExtra(id_centro);

        Intent inten6 = getIntent();
        nombre_cursi = inten6.getStringExtra(nombre_curso);

        Intent inten7 = getIntent();
        descripci = inten7.getStringExtra(descripcion);

        Intent inten8 = getIntent();
        curso_id = inten8.getStringExtra(cursi_id);

        a.setText(nombre_curso);
        b.setText(descripcion);


        if (id_centro == null) {
            id_centro = "0";
        }
    }

    public void salirperfil(View view) {

        Intent an = new Intent(this, CursosDocente.class);
        nombre_curso = "";
        descripcion = "";
        startActivity(an);
    }

    public void anadir_curso(View view) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        String nombre = a.getText().toString();
        String descripcio = b.getText().toString();


        if (!nombre.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("NOMBRE_CURSO", nombre);
            registro.put("DESCRIPCION", descripcio);
            registro.put("CENTRO_EDUCATIVO_ID", id_centro);
            registro.put("DOCENTE_ID", id);


            basededatos.insert("CURSO", null, registro);

            basededatos.close();

            Toast.makeText(this, "Curso Insertado", Toast.LENGTH_SHORT).show();

            Map<String,Object> map= new HashMap<String,Object>();
            map.put(a.getText().toString(),"");
            root.updateChildren(map);

            Intent an = new Intent(this, CursosDocente.class);
            nombre_curso = "";
            descripcion = "";
            startActivity(an);

        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void actualizar_curso(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();

        String nombr_curso = a.getText().toString();
        String descripcion_curso = b.getText().toString();


        if (!nombr_curso.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("NOMBRE_CURSO", nombr_curso);
            registro.put("DESCRIPCION", descripcion_curso);


            basededatos.update("CURSO", registro, "CURSO_ID='" + cursi_id + "'", null);
            basededatos.close();

            Toast.makeText(this, "El usuario se ha modificado correctamente", Toast.LENGTH_SHORT).show();
            Intent an = new Intent(this, CursosDocente.class);
            nombre_curso = "";
            descripcion = "";
            startActivity(an);



        }
    }
}

