package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.myapplication.DatosAlumno.et;
import static com.example.myapplication.DatosAlumno.nt;
import static com.example.myapplication.DatosAlumno.pt;
import static com.example.myapplication.DatosAlumno.st;
import static com.example.myapplication.DatosAlumno.telefono_tutor;
import static com.example.myapplication.DatosAlumno.tt;

public class TutoresLegales extends AppCompatActivity {
EditText a,b,c,d,e;
 public static String a1,b1,c1,d1,e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutores_legales);
        a = findViewById(R.id.nombre_tutor);
        b = findViewById(R.id.primer_apellido_tutor);
        c = findViewById(R.id.segundo_apellido_tutor);
        d = findViewById(R.id.email_tutor);
        e = findViewById(R.id.telefono_tutor);
if(nt!=null){
    a.setText(nt);
}
if(pt!=null){
    b.setText(pt);
}
if(st!=null){
    c.setText(st);
}
if(et!=null){
    d.setText(et);
}
if(telefono_tutor!=null){
    e.setText(telefono_tutor);
}
    }

    public void salirtutores(View view) {
        Intent an = new Intent(this, DatosAlumno.class);
        startActivity(an);
    }


    public void pasardatos(View view) {
        a1 = a.getText().toString();
        b1 = b.getText().toString();
        c1 = c.getText().toString();
        d1 = d.getText().toString();
        e1 =  e.getText().toString();

        if(!a1.isEmpty()&& !b1.isEmpty()&&!c1.isEmpty() && !d1.isEmpty() && !e1.isEmpty()) {





            Intent an = new Intent(this, TutoresLegales2.class);
            startActivity(an);
        }else{
            Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            a.setText("");
            b.setText("");
            c.setText("");
            d.setText("");
            e.setText("");
        }
    }
}
