package com.example.myapplication;

public class Alumno {
    Integer alumno_id,rol_id,curso_id,docente_id;
    String nombre_alumno,primer_apellido,segundo_apellido,contraseña,email,nombre_curso,observaciones;

    public Alumno() {
    }

    public Alumno(Integer alumno_id, Integer rol_id, Integer curso_id, Integer docente_id, String nombre_alumno, String primer_apellido, String segundo_apellido, String contraseña, String email, String nombre_curso, String observaciones) {
        this.alumno_id = alumno_id;
        this.rol_id = rol_id;
        this.curso_id = curso_id;
        this.docente_id = docente_id;
        this.nombre_alumno = nombre_alumno;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.contraseña = contraseña;
        this.email = email;
        this.nombre_curso = nombre_curso;
        this.observaciones = observaciones;
    }

    public Integer getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(Integer alumno_id) {
        this.alumno_id = alumno_id;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    public Integer getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(Integer curso_id) {
        this.curso_id = curso_id;
    }

    public Integer getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(Integer docente_id) {
        this.docente_id = docente_id;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
