package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import java.util.ArrayList;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context) {
        super(context, "sergi.db", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase basededatos) {
        basededatos.execSQL("create table DOCENTE(DOCENTE_ID integer  primary key autoincrement  not null, NOMBRE text not null unique, CONTRASENA text not null, EMAIL text not null unique, ROL_ID integer not null)");
        basededatos.execSQL("create table  ROL(ROL_ID integer primary key autoincrement, NOMBRE_ROL text)");
        basededatos.execSQL("create table  CENTRO_EDUCATIVO(CENTRO_EDUCATIVO_ID integer primary key autoincrement not null, NOMBRE_CENTRO text not null, EMAIL_CENTRO text, TELEFONO text, NUMERO text, CALLE text, MUNICIPIO text, LOCALIDAD text, PROVINCIA text, CODIGO_POSTAL text, DOCENTE_ID integer not null)");
        basededatos.execSQL("create table CURSO(CURSO_ID integer primary key autoincrement not null, NOMBRE_CURSO text not null, DESCRIPCION text, CENTRO_EDUCATIVO_ID integer, DOCENTE_ID integer not null)");
        basededatos.execSQL("create table ALUMNO(ALUMNO_ID integer primary key autoincrement not null, NOMBRE_ALUMNO text not null, PRIMER_APELLIDO text not null, SEGUNDO_APELLIDO text,CONTRASENA_ALUMNO text not null, EMAIL_ALUMNO text, OBSERVACIONES text,  ROL_ID integer not null, CURSO_ID integer not null, NOMBRE_CURSO text not null, DOCENTE_ID integer not null)");
        basededatos.execSQL("create table TUTOR_LEGAL (TUTOR_LEGAL_ID integer primary key autoincrement not null, NOMBRE_TUTOR text not null, PRIMER_APELLIDO text not null, SEGUNDO_APELLIDO text, EMAIL text not null, TELEFONO integer not null, NUMERO text not null, CALLE text not null, MUNICIPIO text, LOCALIDAD text not null, PROVINCIA text not null, CODIGO_POSTAL integer not null, OBSERVACIONES text, ALUMNO_ID text not null)");
        basededatos.execSQL("create table TAREA(TAREA_ID integer primary key autoincrement not null, NOMBRE_TAREA text NOT NULL, INSTRUCCIONES text, FECHA_LIMITE text, RUBRICA_ID integer, CURSO_ID integer not null, DOCENTE_ID integer not null)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists DOCENTE");
        db.execSQL("drop table if exists ROL");
        db.execSQL("drop table if exists CENTRO_EDUCATIVO");
        db.execSQL("drop table if exists CURSO");
        db.execSQL("drop table if exists ALUMNO");
        db.execSQL("drop table if exists TAREA");
    }

    public Boolean checkusuariopass(String nombre, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from  DOCENTE where NOMBRE=? and CONTRASENA=?", new String[]{nombre, contrasena});
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;


        } else return false;


    }
    public Boolean checkalumnopass(String nombre, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from  ALUMNO where NOMBRE_ALUMNO=? and CONTRASENA_ALUMNO=?", new String[]{nombre, contrasena});
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;


        } else return false;


    }

    public Boolean acceso(String nombre, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select ROL_ID from  DOCENTE where NOMBRE=? and CONTRASENA=?", new String[]{nombre, contrasena});
        if (cursor != null && cursor.moveToNext()) {
            cursor.moveToFirst();
            do {
                String var = cursor.getString(cursor.getColumnIndex("ROL_ID"));
                if (var.equals("1")) {
                    return true;
                } else return false;
            } while (cursor.moveToNext());


        } else {
            cursor.close();
            db.close();
            return false;
        }
    }
    public Boolean accesoalumnos(String nombre, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select ROL_ID from  ALUMNO where NOMBRE_ALUMNO=? and CONTRASENA_ALUMNO=?", new String[]{nombre, contrasena});
        if (cursor != null && cursor.moveToNext()) {
            cursor.moveToFirst();
            do {
                String var = cursor.getString(cursor.getColumnIndex("ROL_ID"));
                if (var.equals("2")) {
                    return true;
                } else return false;
            } while (cursor.moveToNext());


        } else {
            cursor.close();
            db.close();
            return false;
        }
    }
}






