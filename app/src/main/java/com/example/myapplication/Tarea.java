package com.example.myapplication;

public class Tarea
{
    Integer tarea_id,rubrica_id,curso_id,docente_id;
    String nombre_tarea,instrucciones,fecha_limite;

    public Tarea() {
    }

    public Tarea(Integer tarea_id, Integer rubrica_id, Integer curso_id, Integer docente_id, String nombre_tarea, String instrucciones, String fecha_limite) {
        this.tarea_id = tarea_id;
        this.rubrica_id = rubrica_id;
        this.curso_id = curso_id;
        this.docente_id = docente_id;
        this.nombre_tarea = nombre_tarea;
        this.instrucciones = instrucciones;
        this.fecha_limite = fecha_limite;
    }

    public Integer getTarea_id() {
        return tarea_id;
    }

    public void setTarea_id(Integer tarea_id) {
        this.tarea_id = tarea_id;
    }

    public Integer getRubrica_id() {
        return rubrica_id;
    }

    public void setRubrica_id(Integer rubrica_id) {
        this.rubrica_id = rubrica_id;
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

    public String getNombre_tarea() {
        return nombre_tarea;
    }

    public void setNombre_tarea(String nombre_tarea) {
        this.nombre_tarea = nombre_tarea;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }
}
