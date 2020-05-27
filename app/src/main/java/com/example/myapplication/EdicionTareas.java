package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.myapplication.CursosTareasAlumnos.c;
import static com.example.myapplication.MainActivity.id;
public class EdicionTareas extends AppCompatActivity {
public static TextView a;
private DatePickerDialog.OnDateSetListener mDateSetListener;
private static final String TAG = "EdicionTareas";
EditText nombre_tarea,instru;
public static int rubric=0;
public static String nom_t,ins,date;
Switch witch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_tareas);
        a = (TextView) findViewById(R.id.fecha_entrega);
        nombre_tarea = (EditText) findViewById(R.id.nombre_tarea);
        instru = (EditText) findViewById(R.id.instrucciones);
        witch = (Switch) findViewById(R.id.switch1);
        witch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(EdicionTareas.this,VisionRubricas.class);
               startActivity(intent);
                } else {
                }
            }
        });
    }

    public void salirediciontareas(View view) {
        Intent intent = new Intent(EdicionTareas.this,TareasDocente.class);
        startActivity(intent);
    }
    public void fecha(View view) {
        Calendar cal = Calendar.getInstance();
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
            EdicionTareas.this,android.R.style.Theme_Holo_Dialog_MinWidth,mDateSetListener,año,dia,mes);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int año, int mes, int dia) {
                mes = mes+1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + mes +  "/" + dia + "/" + año  );
                 date = mes + "/" + dia + "/" + año;
                a.setText(date);
            }
        };
        }

    public void crearub(View view) {

    }

    public void añadirtarea(View view) {
        nom_t = nombre_tarea.getText().toString();
        ins = instru.getText().toString();



        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();



        if (!nom_t.isEmpty() ) {

            ContentValues registro = new ContentValues();
            registro.put("NOMBRE_TAREA", nom_t);
            registro.put("INSTRUCCIONES", ins);
            registro.put("FECHA_LIMITE", date);
            registro.put("RUBRICA_ID", rubric);
            registro.put("CURSO_ID", c);
            registro.put("DOCENTE_ID", id);



            basededatos.insert("TAREA", null, registro);

            basededatos.close();

            Toast.makeText(this, "Tarea Insertada", Toast.LENGTH_SHORT).show();


            Intent an = new Intent(this, TareasDocente.class);
            Toast.makeText(this, nom_t + "" + ins+ "" +date+ "" +rubric+ "" +c+ "" +id, Toast.LENGTH_SHORT).show();
            startActivity(an);

        } else {
            Toast.makeText(this, "Debe rellenar todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            nombre_tarea.setText("");
            instru.setText("");
            a.setText("");

        }
    }
}



