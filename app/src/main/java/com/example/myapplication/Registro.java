package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;



public class Registro extends AppCompatActivity {
    AdminSQLiteOpenHelper db;
ImageButton b1;
Button registrarse;
EditText usuario,email,contrasena;
Button login2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        b1 = (ImageButton) findViewById(R.id.volver);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, MainActivity.class);
                startActivity(intent);
            }
        });
        registrarse = (Button) findViewById(R.id.login2);
        usuario = (EditText) findViewById(R.id.usuario);
        email = (EditText) findViewById(R.id.email);
        contrasena = (EditText) findViewById(R.id.contrasena);
        login2 = (Button) findViewById(R.id.login2);

}

    public void Registrar(View view) {



        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
        String usuarios = usuario.getText().toString();
        String contrasenas = contrasena.getText().toString();
        String emails = email.getText().toString();
        Integer rol = 1;

        if (!usuarios.isEmpty() && !contrasenas.isEmpty() && !emails.isEmpty()) {

                ContentValues registro = new ContentValues();
                registro.put("NOMBRE", usuarios);
                registro.put("CONTRASENA", contrasenas);
                registro.put("EMAIL", emails);
                registro.put("ROL_ID", rol);


                basededatos.insert("DOCENTE", null, registro);

                basededatos.close();
                usuario.setText("");
                contrasena.setText("");
                email.setText("");


                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            Intent reg = new Intent(this, MainActivity.class);
            startActivity(reg);

            }
         else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

}
