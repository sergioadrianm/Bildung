package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.hash.sha;
import com.example.componentead.AdminSQLiteOpenHelper;
import com.example.operacionesalumnos.PerfilAlumno;
import com.example.operacionesdocente.CentroEducativo;
import com.example.operacionesdocente.EdicionAlumnos;
import com.example.operacionesdocente.EdicionCursos;
import com.example.operacionesdocente.EliminarCursos;
import com.example.operacionesdocente.PerfilDocente;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
    Button b1, b2;
    EditText usuariosesio, contrasenasesio;
    public static final String usuario = "";
    public static final String emaildocente = "";
    AdminSQLiteOpenHelper db;
    int cont = 0;
    static String well;
    public static String e1, e2, em, id, id_alumno, primer_apellida, segundo_apellido;
    public String getWell() {
        return well;
    }
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationView = findViewById(R.id.inicio);
        animationView.playAnimation();
        db = new AdminSQLiteOpenHelper(this);
        contrasenasesio = (EditText) findViewById(R.id.contrasenasesion);
        usuariosesio = (EditText) findViewById(R.id.usuariosesion);
        b1 = (Button) findViewById(R.id.login1);
        b2 = (Button) findViewById(R.id.iniciosesion);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                e1 = usuariosesio.getText().toString();
                e2 = contrasenasesio.getText().toString();
                byte[] inputData = e2.getBytes();
                byte[] outputData = new byte[0];
                try {
                    outputData = sha.encryptSHA(inputData, "SHA-1");
                } catch (Exception e) {
                    e.printStackTrace();

                }
                BigInteger a = new BigInteger(1, outputData);

                Boolean checknompwd = db.checkusuariopass(e1, a.toString(16));
                Boolean checkstudent = db.checkalumnopass(e1, a.toString(16));
                if (checknompwd == true || checkstudent == true) {
                    cont++;

                    if (cont == 1) {

                        e1 = usuariosesio.getText().toString();
                        Boolean acces = db.acceso(e1, a.toString(16));
                        Boolean accesoa = db.accesoalumnos(e1, a.toString(16));

                        if (acces == true) {
                            Toast.makeText(getApplicationContext(), "Inicio de sesion correcto", Toast.LENGTH_SHORT).show();

                            Intent pas = new Intent(MainActivity.this, Docentes.class);
                            Intent pa = new Intent(MainActivity.this, PerfilDocente.class);
                            pa.putExtra(usuario, e1);
                            Intent pa4 = new Intent(MainActivity.this, CursosDocente.class);
                            pa4.putExtra(usuario, e1);
                            startActivity(pas);

                            cont--;
                            well = usuariosesio.getText().toString();
                            emaildocente();
                            sacarid();
                        }


                        if (accesoa == true) {

                            Toast.makeText(getApplicationContext(), "Inicio de sesion correcto", Toast.LENGTH_SHORT).show();
                            Intent pa = new Intent(MainActivity.this, CursosChatAlumnos.class);
                            pa.putExtra(usuario, e1);
                            Intent pas = new Intent(MainActivity.this, AccesoAlumnos.class);
                            startActivity(pas);
                            cont--;
                            well = usuariosesio.getText().toString();
                            sacaridalumno();

                        }


                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    usuariosesio.setText("");
                    contrasenasesio.setText("");
                }
            }
        });
    }

    private void sacaridalumno() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select ALUMNO_ID,PRIMER_APELLIDO,SEGUNDO_APELLIDO from ALUMNO where NOMBRE_ALUMNO='" + e1 + "'", null);

        if (fila.moveToFirst()) {
            do {
                id_alumno = fila.getString(fila.getColumnIndex("ALUMNO_ID"));
                primer_apellida = fila.getString(fila.getColumnIndex("PRIMER_APELLIDO"));
                segundo_apellido = fila.getString(fila.getColumnIndex("SEGUNDO_APELLIDO"));
                basededatos.close();

                Intent pa12 = new Intent(MainActivity.this, AccesoAlumnos.class);
                pa12.putExtra(id_alumno, id_alumno);
                Intent pa13 = new Intent(MainActivity.this, PerfilAlumno.class);
                pa13.putExtra(id_alumno, id_alumno);
                Intent pa14 = new Intent(MainActivity.this, CursosChatAlumnos.class);
                pa14.putExtra(id_alumno, id_alumno);


            } while (fila.moveToNext());
        } else {
            basededatos.close();
        }
    }


    public void emaildocente() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select EMAIL from DOCENTE where NOMBRE='" + e1 + "'", null);

        if (fila.moveToFirst()) {
            do {

                em = fila.getString(fila.getColumnIndex("EMAIL"));
                basededatos.close();

                Intent pa3 = new Intent(MainActivity.this, PerfilDocente.class);
                pa3.putExtra(emaildocente, em);
            } while (fila.moveToNext());
        } else {
            basededatos.close();
        }

    }

    public void sacarid() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select DOCENTE_ID from DOCENTE where NOMBRE='" + e1 + "'", null);

        if (fila.moveToFirst()) {
            do {
                id = fila.getString(fila.getColumnIndex("DOCENTE_ID"));
                basededatos.close();

                Intent pa12 = new Intent(MainActivity.this, CentroEducativo.class);
                pa12.putExtra(id, id);
                Intent pa13 = new Intent(MainActivity.this, EdicionCursos.class);
                pa13.putExtra(id, id);
                Intent pa14 = new Intent(MainActivity.this, EdicionAlumnos.class);
                pa14.putExtra(id, id);
                Intent pa15 = new Intent(MainActivity.this, EliminarCursos.class);
                pa15.putExtra(id, id);

            } while (fila.moveToNext());
        } else {
            basededatos.close();
        }
    }


    public void pasaregistro(View view) {

        Intent ci = new Intent(this, Registro.class);
        startActivity(ci);
    }
}



