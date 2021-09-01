/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.Date;

/**
 *
 * @author alpha
 */
public class mueble {
    private String nombre;
    private float precioVenta;
    private float precioEnsamble;
    private Date ensamble;

    public mueble(String nombre, float precioVenta, float precioEnsamble) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.precioEnsamble = precioEnsamble;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getPrecioEnsamble() {
        return precioEnsamble;
    }

    public void setPrecioEnsamble(float precioEnsamble) {
        this.precioEnsamble = precioEnsamble;
    }
    
    
}
