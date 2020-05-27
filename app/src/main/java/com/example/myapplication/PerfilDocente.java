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


import static com.example.myapplication.Docentes.cont;
import static com.example.myapplication.Docentes.corr;
import static com.example.myapplication.Docentes.no;


public class PerfilDocente extends AppCompatActivity {
    EditText b, c, d;
    public static String usuarios, contrasena, email, usr,user;

    public static final String usuarior ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_docente);
        b = findViewById(R.id.usuarioper);
        c = findViewById(R.id.contrasenaper);
        d = findViewById(R.id.emailper);
        Intent intent = getIntent();
        usuarios = intent.getStringExtra(no);
        b.setText(no);
        user=b.getText().toString();

        Intent inten = getIntent();
        contrasena = inten.getStringExtra(cont);
        c.setText(cont);

        Intent inten2 = getIntent();
        email = inten2.getStringExtra(corr);
        d.setText(corr);

    }


    public void salirperfil(View view) {
        Intent an = new Intent(this, Docentes.class);
        Intent pa3 = new Intent(PerfilDocente.this, Docentes.class);
        pa3.putExtra(usuarior, usr);
        startActivity(an);

    }

    public void actualizardoc(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();

        String nombres = b.getText().toString();
        String contrasenas = c.getText().toString();
        String emails = d.getText().toString();


       if (!nombres.isEmpty() && !contrasenas.isEmpty() && !emails.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("NOMBRE", nombres);
            registro.put("CONTRASENA", contrasenas);
            registro.put("EMAIL", emails);


            basededatos.update("DOCENTE", registro, "NOMBRE='" + user + "'", null);
            basededatos.close();

            Toast.makeText(this, "El usuario se ha modificado correctamente", Toast.LENGTH_SHORT).show();

             admin = new AdminSQLiteOpenHelper(this);
             basededatos = admin.getWritableDatabase();


            Cursor fila = basededatos.rawQuery("select NOMBRE from DOCENTE where NOMBRE='" + nombres + "'", null);

            if (fila.moveToFirst()) {
                do {

                    usr = fila.getString(fila.getColumnIndex("NOMBRE"));
                    basededatos.close();

                } while (fila.moveToNext());
            } else {
                basededatos.close();
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}
