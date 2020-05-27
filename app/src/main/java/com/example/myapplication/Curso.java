package com.example.myapplication;

public class Curso {
    Integer curso_id,centro_educativo_id,docente_id;
    String nombre_curso,descripcion;

    public Curso() {
    }

    public Curso(Integer curso_id, Integer centro_educativo_id, Integer docente_id, String nombre_curso, String descripcion) {
        this.curso_id = curso_id;
        this.centro_educativo_id = centro_educativo_id;
        this.docente_id = docente_id;
        this.nombre_curso = nombre_curso;
        this.descripcion = descripcion;
    }

    public Integer getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Integer curso_id) {
        this.curso_id = curso_id;
    }

    public Integer getCentro_educativo_id() {
        return centro_educativo_id;
    }

    public void setCentro_educativo_id(Integer centro_educativo_id) {
        this.centro_educativo_id = centro_educativo_id;
    }

    public Integer getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(Integer docente_id) {
        this.docente_id = docente_id;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
