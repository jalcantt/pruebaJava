package com.prueba.alvaro;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

//@Data
public class Producto {
    private Integer id;
    private String nombre;
    private Date fechaResgistro;

    public Producto() {
    }

    public Producto(Integer id, String nombre, Date fechaResgistro) {
        this.id = id;
        this.nombre = nombre;
        this.fechaResgistro = fechaResgistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaResgistro() {
        return fechaResgistro;
    }

    public void setFechaResgistro(Date fechaResgistro) {
        this.fechaResgistro = fechaResgistro;
    }
}
