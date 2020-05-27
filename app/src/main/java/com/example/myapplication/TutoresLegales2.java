package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static com.example.myapplication.Alumnos.cursio;
import static com.example.myapplication.CentroEducativo.id_centro;
import static com.example.myapplication.DatosAlumno.cp_t;
import static com.example.myapplication.DatosAlumno.ct;
import static com.example.myapplication.DatosAlumno.lt;
import static com.example.myapplication.DatosAlumno.mt;
import static com.example.myapplication.DatosAlumno.nt;
import static com.example.myapplication.DatosAlumno.nut;
import static com.example.myapplication.DatosAlumno.ot;
import static com.example.myapplication.DatosAlumno.prt;
import static com.example.myapplication.MainActivity.id;
import static com.example.myapplication.ModificarCursos.cursi_id;
import static com.example.myapplication.ModificarCursos.descripcion;
import static com.example.myapplication.ModificarCursos.nombre_curso;
import static com.example.myapplication.TutoresLegales.a1;
import static com.example.myapplication.TutoresLegales.b1;
import static com.example.myapplication.TutoresLegales.c1;
import static com.example.myapplication.TutoresLegales.d1;
import static com.example.myapplication.TutoresLegales.e1;

public class TutoresLegales2 extends AppCompatActivity {
EditText f,g,h,i,j,k,l;
public static String f1,g1,h1,i1,j1,k1,l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutores_legales2);

        f = findViewById(R.id.numero_tutor);
        g = findViewById(R.id.calle_tutor);
        h = findViewById(R.id.municipio_tutor);
        i = findViewById(R.id.localidad_tutor);
        j = findViewById(R.id.provincia_tutor);
        k = findViewById(R.id.codigo_postal_tutor);
        l = findViewById(R.id.observaciones_tutor);

        if(nut!=null){
            f.setText(nt);
        }
        if(ct!=null){
            g.setText(ct);
        }
        if(mt!=null){
            h.setText(mt);
        }
        if(lt!=null){
            i.setText(lt);
        }
        if(prt!=null){
            j.setText(prt);
        }
        if(cp_t!=null){
            k.setText(cp_t);
        }
        if(ot!=null){
            l.setText(ot);
        }


    }

    public void irtutores(View view) {
        Intent an = new Intent(this, TutoresLegales.class);
        startActivity(an);

    }

    public void actualizar_tutores(View view) {
        f1 = f.getText().toString();
        g1 = g.getText().toString();
        h1 = h.getText().toString();
        i1 = i.getText().toString();
        j1 =  j.getText().toString();
        k1 =  k.getText().toString();
        l1 =  l.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();



        if (!f1.isEmpty() && !g1.isEmpty() && !i1.isEmpty() && !j1.isEmpty() && !k1.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("NOMBRE_TUTOR", a1);
            registro.put("PRIMER_APELLIDO", b1);
            registro.put("SEGUNDO_APELLIDO", c1);
            registro.put("EMAIL", d1);
            registro.put("TELEFONO", e1);
            registro.put("NUMERO", f1);
            registro.put("CALLE", g1);
            registro.put("MUNICIPIO", h1);
            registro.put("LOCALIDAD", i1);
            registro.put("PROVINCIA", j1);
            registro.put("CODIGO_POSTAL", k1);
            registro.put("OBSERVACIONES", l1);
            registro.put("ALUMNO_ID", cursio);

            basededatos.update("TUTOR_LEGAL", registro, "ALUMNO_ID='" + cursio + "'", null);
            basededatos.close();

            Toast.makeText(this, "El tutor se ha modificado correctamente", Toast.LENGTH_SHORT).show();
            Intent an = new Intent(this, Alumnos.class);
            startActivity(an);
        }else {
                Toast.makeText(this, "Debe rellenar todos los campos obligatorios", Toast.LENGTH_SHORT).show();
                f.setText("");
                g.setText("");
                h.setText("");
                i.setText("");
                j.setText("");
                k.setText("");
                l.setText("");

            }


        }


    public void añadirtutores(View view) {
        f1 = f.getText().toString();
        g1 = g.getText().toString();
        h1 = h.getText().toString();
        i1 = i.getText().toString();
        j1 =  j.getText().toString();
        k1 =  k.getText().toString();
        l1 =  l.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();



        if (!f1.isEmpty() && !g1.isEmpty() && !i1.isEmpty() && !j1.isEmpty() && !k1.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("NOMBRE_TUTOR", a1);
            registro.put("PRIMER_APELLIDO", b1);
            registro.put("SEGUNDO_APELLIDO", c1);
            registro.put("EMAIL", d1);
            registro.put("TELEFONO", e1);
            registro.put("NUMERO", f1);
            registro.put("CALLE", g1);
            registro.put("MUNICIPIO", h1);
            registro.put("LOCALIDAD", i1);
            registro.put("PROVINCIA", j1);
            registro.put("CODIGO_POSTAL", k1);
            registro.put("OBSERVACIONES", l1);
            registro.put("ALUMNO_ID", cursio);



            basededatos.insert("TUTOR_LEGAL", null, registro);

            basededatos.close();

            Toast.makeText(this, "Tutor Insertado", Toast.LENGTH_SHORT).show();


            Intent an = new Intent(this, Alumnos.class);
            startActivity(an);

        } else {
            Toast.makeText(this, "Debe rellenar todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            f.setText("");
            g.setText("");
            h.setText("");
            i.setText("");
            j.setText("");
            k.setText("");
            l.setText("");

        }

    }
}
