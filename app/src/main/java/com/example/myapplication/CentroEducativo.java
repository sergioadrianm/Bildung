package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.myapplication.Docentes.cac;
import static com.example.myapplication.Docentes.cpc;
import static com.example.myapplication.Docentes.emac;
import static com.example.myapplication.Docentes.loc;
import static com.example.myapplication.Docentes.munc;
import static com.example.myapplication.Docentes.numc;
import static com.example.myapplication.Docentes.provc;
import static com.example.myapplication.Docentes.telc;
import static com.example.myapplication.MainActivity.id;
public class CentroEducativo extends AppCompatActivity {
EditText a,b,c,d,e,f,g,h,i;
Button ab,bc;
    public static final String centro="";
    public static String nom,em,tf,num,ca,mun,loca,pro,cp,idd;
public static String ce,ceo,id_centro;
String nombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.centro_educativo);
        a = findViewById(R.id.nombre_centro);
        b = findViewById(R.id.email_centro);
        c = findViewById(R.id.telefono_centro);
        d = findViewById(R.id.numero_centro);
        e = findViewById(R.id.calle_centro);
        f = findViewById(R.id.municipio_centro);
        g = findViewById(R.id.localidad_centro);
        h = findViewById(R.id.provincia_centro);
        i = findViewById(R.id.codigo_postal_centro);
        ab = findViewById(R.id.anadir_centro);
        bc= findViewById(R.id.actualizar_centro);


        Intent inten = getIntent();
        nom = inten.getStringExtra(ce);
        a.setText(ce);

        Intent inten2 = getIntent();
        em = inten2.getStringExtra(emac);
        b.setText(emac);

        Intent inten3 = getIntent();
        tf = inten3.getStringExtra(telc);
        c.setText(telc);

        Intent inten4 = getIntent();
        num = inten4.getStringExtra(numc);
        d.setText(numc);

        Intent inten5 = getIntent();
        ca = inten5.getStringExtra(cac);
        e.setText(cac);

        Intent inten6 = getIntent();
        mun = inten6.getStringExtra(munc);
        f.setText(munc);

        Intent inten7 = getIntent();
        loca = inten7.getStringExtra(loc);
        g.setText(loc);

        Intent inten8 = getIntent();
        pro = inten8.getStringExtra(provc);
        h.setText(provc);

        Intent inten9 = getIntent();
        cp = inten9.getStringExtra(cpc);
        i.setText(cpc);

        Intent inten10 = getIntent();
        idd = inten10.getStringExtra(id);

    }


    public void salirperfil(View view) {
        Intent an = new Intent(this, Docentes.class);
        sacarnombre();
        startActivity(an);
    }

    public void anadir_centro(View view) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();
         nombres = a.getText().toString();
        String emails = b.getText().toString();
        String telefonos = c.getText().toString();
        String numeros = d.getText().toString();
        String calles = e.getText().toString();
        String municipios = f.getText().toString();
        String localidades= g.getText().toString();
        String provincias = h.getText().toString();
        String cps = i.getText().toString();

        if (!nombres.isEmpty() ) {
            ContentValues registro = new ContentValues();
            registro.put("NOMBRE_CENTRO", nombres);
            registro.put("EMAIL_CENTRO", emails);
            registro.put("TELEFONO", telefonos);
            registro.put("NUMERO", numeros);
            registro.put("CALLE", calles);
            registro.put("MUNICIPIO", municipios);
            registro.put("LOCALIDAD", localidades);
            registro.put("PROVINCIA", provincias);
            registro.put("CODIGO_POSTAL", cps);
            registro.put("DOCENTE_ID",id);


            basededatos.insert("CENTRO_EDUCATIVO", null, registro);


            Toast.makeText(this, "Centro Insertado", Toast.LENGTH_SHORT).show();

            centro_id();
            basededatos.close();
        }
        ce = a.getText().toString();
    }

    public void actualizar_centro(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase basededatos = admin.getWritableDatabase();

        String nombres = a.getText().toString();
        String emails = b.getText().toString();
        String telefonos = c.getText().toString();
        String numeros = d.getText().toString();
        String calles = e.getText().toString();
        String municipios = f.getText().toString();
        String localidades= g.getText().toString();
        String provincias = h.getText().toString();
        String cps = i.getText().toString();


            ContentValues registro = new ContentValues();
        registro.put("NOMBRE_CENTRO", nombres);
        registro.put("EMAIL_CENTRO", emails);
        registro.put("TELEFONO", telefonos);
        registro.put("NUMERO", numeros);
        registro.put("CALLE", calles);
        registro.put("MUNICIPIO", municipios);
        registro.put("LOCALIDAD", localidades);
        registro.put("PROVINCIA", provincias);
        registro.put("CODIGO_POSTAL", cps);
        registro.put("DOCENTE_ID", id);

            basededatos.update("CENTRO_EDUCATIVO", registro, "NOMBRE_CENTRO='" + ce + "'", null);
            basededatos.close();

            Toast.makeText(this, "El centro se ha modificado correctamente", Toast.LENGTH_SHORT).show();
        ceo = a.getText().toString();

            basededatos.close();
            }
           public void sacarnombre() {

if(ceo!=null){
    ce=ceo;
    Intent pa3 = new Intent(CentroEducativo.this,Docentes.class);
    pa3.putExtra(centro, ce);
}
else {
    Intent pa3 = new Intent(CentroEducativo.this, Docentes.class);
    pa3.putExtra(centro, ce);
}

    }
public void centro_id(){

        AdminSQLiteOpenHelper   admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase  basededatos = admin.getWritableDatabase();


        Cursor fila = basededatos.rawQuery("select CENTRO_EDUCATIVO_ID from CENTRO_EDUCATIVO where NOMBRE_CENTRO='" + nombres + "'", null);

        if(fila.moveToFirst() ) {
            do {
                id_centro  = fila.getString(fila.getColumnIndex("CENTRO_EDUCATIVO_ID"));
                basededatos.close();


                Intent pa13 = new Intent(CentroEducativo.this, EdicionCursos.class);
                pa13.putExtra(id_centro, id_centro);

            }while(fila.moveToNext());
        }else{
            basededatos.close();
        }
}

}






