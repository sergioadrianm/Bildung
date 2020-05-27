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

import static com.example.myapplication.Alumnos.cursio;
import static com.example.myapplication.Alumnos.emailalumno;
import static com.example.myapplication.Alumnos.nomalumno;
import static com.example.myapplication.Alumnos.obser;
import static com.example.myapplication.Alumnos.primerape;
import static com.example.myapplication.Alumnos.pwd;
import static com.example.myapplication.Alumnos.segundoape;
import static com.example.myapplication.CursosDocente.cursi;

public class DatosAlumno extends AppCompatActivity {
String nom,unoape,dosape,pwdi,email,observ;
EditText a,b,c,d,e,f;
String alumno_id;
public static String nt,pt,st,et,nut,ct,lt,mt,prt,ot;
public static int tt,cpt;
    public static String telefono_tutor,cp_t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_alumno);
        a = findViewById(R.id.nombre_alumno);
        b = findViewById(R.id.primer_apellido_alumno);
        c = findViewById(R.id.segundo_apellido_alumno);
        d = findViewById(R.id.contraseña_alumno);
        e = findViewById(R.id.email_alumno);
        f = findViewById(R.id.observaciones_alumno);
        Intent inten3 = getIntent();
        nom = inten3.getStringExtra(nomalumno);
        a.setText(nomalumno);
        Intent inten4 = getIntent();
        unoape = inten4.getStringExtra(primerape);
        b.setText(primerape);
        Intent inten5 = getIntent();
        dosape = inten5.getStringExtra(segundoape);
        c.setText(segundoape);
        Intent inten6 = getIntent();
        pwdi = inten6.getStringExtra(pwd);
        d.setText(pwd);
        Intent inten7 = getIntent();
        email = inten7.getStringExtra(emailalumno);
        e.setText(emailalumno);
        Intent inten8 = getIntent();
        observ = inten8.getStringExtra(obser);
        f.setText(obser);

        Intent inten9 = getIntent();
        alumno_id = inten9.getStringExtra(cursi);

    }

    public void salirmodificacionalumnos(View view) {
        Intent an = new Intent(this, Alumnos.class);
        startActivity(an);
    }

    public void actualizar_alumnos(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();

        String nombres = a.getText().toString();
        String primer_ape = b.getText().toString();
        String segundo_ape = c.getText().toString();
        String contrasena = d.getText().toString();
        String  correo= e.getText().toString();
        String observaciones = f.getText().toString();


        if (!nombres.isEmpty() && !primer_ape.isEmpty() && !contrasena.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("NOMBRE_ALUMNO", nombres);
            registro.put("PRIMER_APELLIDO", primer_ape);
            registro.put("SEGUNDO_APELLIDO", segundo_ape);
            registro.put("CONTRASENA_ALUMNO", contrasena);
            registro.put("EMAIL_ALUMNO", correo);
            registro.put("OBSERVACIONES", observaciones);


            basededatos.update("ALUMNO", registro, "ALUMNO_ID='" + cursio + "'", null);
            basededatos.close();
            Intent an = new Intent(this, Alumnos.class);
            startActivity(an);
            Toast.makeText(this, "El usuario se ha modificado correctamente", Toast.LENGTH_SHORT).show();




        } else {
            Toast.makeText(this, "Debes llenar todos los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void tutores_legales(View view) {
        sacardatostutor();
        Intent an = new Intent(this, TutoresLegales.class);
        startActivity(an);
    }

    private void sacardatostutor() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select  NOMBRE_TUTOR, PRIMER_APELLIDO, SEGUNDO_APELLIDO, EMAIL, TELEFONO, NUMERO, CALLE, MUNICIPIO, LOCALIDAD, PROVINCIA, CODIGO_POSTAL, OBSERVACIONES from TUTOR_LEGAL where ALUMNO_ID= '" + cursio + "'", null);
        if (fila != null) {
            if (fila.moveToFirst()) {
                do {
                    nt = fila.getString(fila.getColumnIndex("NOMBRE_TUTOR"));
                    pt = fila.getString(fila.getColumnIndex("PRIMER_APELLIDO"));
                    st = fila.getString(fila.getColumnIndex("SEGUNDO_APELLIDO"));
                    et = fila.getString(fila.getColumnIndex("EMAIL"));
                    tt = fila.getInt(fila.getColumnIndex("TELEFONO"));
                    telefono_tutor = Integer.toString(tt);
                    nut = fila.getString(fila.getColumnIndex("NUMERO"));
                    ct = fila.getString(fila.getColumnIndex("CALLE"));
                    mt = fila.getString(fila.getColumnIndex("MUNICIPIO"));
                    lt = fila.getString(fila.getColumnIndex("LOCALIDAD"));
                    prt = fila.getString(fila.getColumnIndex("PROVINCIA"));
                    cpt = fila.getInt(fila.getColumnIndex("CODIGO_POSTAL"));
                    cp_t = Integer.toString(cpt);
                    ot = fila.getString(fila.getColumnIndex("OBSERVACIONES"));

                    basededatos.close();


                } while (fila.moveToNext());
            }
        }
    }
}
