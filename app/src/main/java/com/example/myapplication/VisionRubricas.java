package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VisionRubricas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vision_rubricas);
    }

    public void volvertarea(View view) {
        Intent intent = new Intent(this,EdicionTareas.class);
        startActivity(intent);
    }
}
