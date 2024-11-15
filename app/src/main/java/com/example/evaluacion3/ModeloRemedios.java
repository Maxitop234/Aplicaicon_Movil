package com.example.evaluacion3;

import java.io.Serializable;

public class ModeloRemedios implements Serializable {

    private String nombre,cantidad,fechavencimiento,mg,categoria,descripcion;
    private int id;
    //Para ID
    public int getId() {
        return id;
    }

    //Para nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Para Cantidad
    public String getCantidad() {
        return cantidad;
    }
    public void setName(String cantidad) {
        this.cantidad = cantidad;
    }

    //Para Fecha de Vencimiento
    public String getFechaVencimiento() {
        return fechavencimiento;
    }
    public void setName(String fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    //Para Miligramos
    public String getMg() {
        return mg;
    }
    public void setMg(String mg) {
        this.mg = mg;
    }

    //Para Categoria
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    //Para Descripcion
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
