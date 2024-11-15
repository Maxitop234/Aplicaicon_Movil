package com.example.evaluacion3;

import java.io.Serializable;

public class ModeloRemedios implements Serializable {

    private String nombre, cantidad, fechavencimiento, mg, categoria, descripcion;
    private int id;

    // Obtener y set Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Get y Set de Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Get y Set de Cantidad
    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    // Get y Set de Fecha de Vencimiento
    public String getFechaVencimiento() {
        return fechavencimiento;
    }
    public void setFechaVencimiento(String fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    // Get y Set de de Mg
    public String getMg() {
        return mg;
    }
    public void setMg(String mg) {
        this.mg = mg;
    }

    // Get y Set de Categoria
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Get y set de Descripcion
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}