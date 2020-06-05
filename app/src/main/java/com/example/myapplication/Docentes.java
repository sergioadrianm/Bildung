package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.componentead.AdminSQLiteOpenHelper;
import com.example.operacionesdocente.CentroEducativo;
import com.example.operacionesdocente.PerfilDocente;
import com.getbase.floatingactionbutton.FloatingActionButton;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static com.example.operacionesdocente.CentroEducativo.ce;
import static com.example.myapplication.MainActivity.e1;
import static com.example.operacionesdocente.PerfilDocente.usr;


public class Docentes extends AppCompatActivity {
    public static String usufinal, usuinicial, usuoficial, no, cont, corr, centroedu;
    public static final String nom = "";
    public static final String con = "";
    public static final String cor = "";
    public static String c = "0";
    public static String nomc, emac, telc, numc, cac, munc, loc, provc, cpc;
    public static boolean galeria;
    TextView a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docentes);
        b = findViewById(R.id.nomusuario);
        Intent inten2 = getIntent();
        usufinal = inten2.getStringExtra(usr);
        Intent inten3 = getIntent();
        usuinicial = inten3.getStringExtra(e1);
        Intent inten4 = getIntent();

        if (validarpermisogaleria()) {
            galeria = true;
        } else {
            galeria = false;
        }

        if (ce != null) {
            centroedu = inten4.getStringExtra(ce);
        }
        c = ce;
        if (usr != null) {
            b.setText(usr);
            usuoficial = b.getText().toString();
        } else {
            b.setText(e1);
            usuoficial = b.getText().toString();
        }
        FloatingActionButton fab7 = (FloatingActionButton) findViewById(R.id.fb7);
        fab7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                actualizacio();
                Intent intent = new Intent(Docentes.this, PerfilDocente.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fab6 = (FloatingActionButton) findViewById(R.id.fb6);
        fab6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Docentes.this, MainActivity.class);
                startActivity(intent);

            }
        });

        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fb3);
        fab3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Docentes.this, CursosDocente.class);
                startActivity(intent);

            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fb1);
        fab1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (ce != null) {
                    actualizaciocentro();

                    Intent intent = new Intent(Docentes.this, CentroEducativo.class);
                    startActivity(intent);
                } else {

                    Intent intent = new Intent(Docentes.this, CentroEducativo.class);
                    startActivity(intent);
                }
            }
        });
        FloatingActionButton fab5 = (FloatingActionButton) findViewById(R.id.fb5);
        fab5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Docentes.this, CursosChat.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fab4 = (FloatingActionButton) findViewById(R.id.fb4);
        fab4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Docentes.this, CalificacionesDocente.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fb2);
        fab2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Docentes.this, CursosTareasAlumnos.class);
                startActivity(intent);

            }
        });
    }

    private boolean validarpermisogaleria() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;
        if (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return true;
        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
            cargarRecomendacion();
        } else {
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, 100);
        }
        return false;
    }

    private void cargarRecomendacion() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Permisos Desactivados");
        alertDialogBuilder.setMessage("Acepte los permisos solicitados para el correcto funcionamiento de la aplicación");
        alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, 100);
            }
        });
        alertDialogBuilder.show();
    }

    private void actualizaciocentro() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select EMAIL_CENTRO,TELEFONO,NUMERO,CALLE,MUNICIPIO,LOCALIDAD,PROVINCIA,CODIGO_POSTAL from CENTRO_EDUCATIVO where NOMBRE_CENTRO= '" + ce + "'", null);
        if (fila != null) {
            if (fila.moveToFirst()) {
                do {
                    emac = fila.getString(fila.getColumnIndex("EMAIL_CENTRO"));
                    telc = fila.getString(fila.getColumnIndex("TELEFONO"));
                    numc = fila.getString(fila.getColumnIndex("NUMERO"));
                    cac = fila.getString(fila.getColumnIndex("CALLE"));
                    munc = fila.getString(fila.getColumnIndex("MUNICIPIO"));
                    loc = fila.getString(fila.getColumnIndex("LOCALIDAD"));
                    provc = fila.getString(fila.getColumnIndex("PROVINCIA"));
                    cpc = fila.getString(fila.getColumnIndex("CODIGO_POSTAL"));
                    basededatos.close();

                    Intent pa = new Intent(Docentes.this, CentroEducativo.class);
                    pa.putExtra(nomc, ce);
                    Intent pa1 = new Intent(Docentes.this, CentroEducativo.class);
                    pa1.putExtra(emac, emac);
                    Intent pa2 = new Intent(Docentes.this, CentroEducativo.class);
                    pa2.putExtra(telc, telc);
                    Intent pa3 = new Intent(Docentes.this, CentroEducativo.class);
                    pa3.putExtra(numc, numc);
                    Intent pa4 = new Intent(Docentes.this, CentroEducativo.class);
                    pa4.putExtra(cac, cac);
                    Intent pa5 = new Intent(Docentes.this, CentroEducativo.class);
                    pa5.putExtra(munc, munc);
                    Intent pa7 = new Intent(Docentes.this, CentroEducativo.class);
                    pa7.putExtra(loc, loc);
                    Intent pa8 = new Intent(Docentes.this, CentroEducativo.class);
                    pa8.putExtra(provc, provc);
                    Intent pa9 = new Intent(Docentes.this, CentroEducativo.class);
                    pa9.putExtra(cpc, cpc);

                } while (fila.moveToNext());
            }
        }
    }


    public void actualizacio() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select NOMBRE,CONTRASENA,EMAIL from DOCENTE where NOMBRE='" + usuoficial + "'", null);

        if (fila.moveToFirst()) {
            do {
                no = fila.getString(fila.getColumnIndex("NOMBRE"));
                cont = fila.getString(fila.getColumnIndex("CONTRASENA"));
                corr = fila.getString(fila.getColumnIndex("EMAIL"));
                basededatos.close();

                Intent pa = new Intent(Docentes.this, PerfilDocente.class);
                pa.putExtra(nom, no);
                Intent pa1 = new Intent(Docentes.this, PerfilDocente.class);
                pa1.putExtra(con, cont);
                Intent pa2 = new Intent(Docentes.this, PerfilDocente.class);
                pa2.putExtra(cor, corr);
            } while (fila.moveToNext());
        }
    }


}
