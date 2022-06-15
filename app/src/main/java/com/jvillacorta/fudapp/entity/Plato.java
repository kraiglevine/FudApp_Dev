package com.jvillacorta.fudapp.entity;

public class Plato {
    private int id;
    private String nombre;
    private String ingredientes;
    private float precio;
    private String descripcion;
    private float oferta;
    private String fecha;
    private String imagenURL;

    public Plato(int id, String nombre, String ingredientes, float precio, String descripcion, float oferta, String fecha, String imagenURL) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.descripcion = descripcion;
        this.oferta = oferta;
        this.fecha = fecha;
        this.imagenURL = imagenURL;
    }

    public Plato(String nombre, String ingredientes, float precio, String descripcion, float oferta, String fecha, String imagenURL) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.descripcion = descripcion;
        this.oferta = oferta;
        this.fecha = fecha;
        this.imagenURL = imagenURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getOferta() {
        return oferta;
    }

    public void setOferta(float oferta) {
        this.oferta = oferta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }
}
