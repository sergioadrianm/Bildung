package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.example.myapplication.CursosChat.nombre;
import static com.example.myapplication.CursosChat.nombre_curso;

public class MensajesDocente extends AppCompatActivity {
String nombrec;
String nombred;
EditText a;
ImageButton enviarm;
TextView mensaje;
    ActionBar actionbar;
    private DatabaseReference root;
   private  String temp_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajes_docente);
        a  = (EditText)findViewById(R.id.editText2);
        enviarm  = (ImageButton) findViewById(R.id.imageButton);
        mensaje  = (TextView) findViewById(R.id.textView21);
        actionbar = getSupportActionBar();
        actionbar.setTitle(nombre_curso);
        Intent inten3 = getIntent();
        nombrec = inten3.getStringExtra(nombre_curso);
        Intent inten4 = getIntent();
        nombred = inten4.getStringExtra(nombre);
        root = FirebaseDatabase.getInstance().getReference().child(nombre_curso);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void salirchat(View view) {
        Intent intent = new Intent(MensajesDocente.this,CursosChat.class);
        startActivity(intent);
    }

    public void enviar_mensaje(View view) {
        Map<String,Object> map= new HashMap<String,Object>();
         temp_key = root.push().getKey();
         root.updateChildren(map);

         DatabaseReference message_root =root.child(temp_key);
        Map<String,Object> map1= new HashMap<String,Object>();
        map1.put("nombre",nombre);
        map1.put("msg",a.getText().toString());
        message_root.updateChildren(map1);

    }
private String chat_msg,chat_user_name;
    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){
            chat_msg = (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot)i.next()).getValue();

            mensaje.append(chat_user_name + " : "+chat_msg+ " \n\n");
        }
    }

}

