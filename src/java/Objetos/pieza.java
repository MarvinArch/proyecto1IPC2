/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author alpha
 */
public class pieza {
    private String nombre;
    private String codigo;
    private String mueble;
    private double precio;
    private int minimo;
    private int cantidad;

    public pieza(String nombre, String codigo, double precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
    }

    public pieza(String nombre, String mueble, int minimo, int cantidad) {
        this.nombre = nombre;
        this.mueble = mueble;
        this.minimo = minimo;
        this.cantidad = cantidad;
    }

    public pieza(String nombre, String codigo, double precio, int cantidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

   
    public pieza(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMueble() {
        return mueble;
    }

    public void setMueble(String mueble) {
        this.mueble = mueble;
    }

    
}
