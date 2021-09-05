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
    protected String identificador;
    protected String nombre;
    protected float precioVenta;
    protected float precioEnsamble;
    protected Date ensamble;
    protected int existencia;

    

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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Date getEnsamble() {
        return ensamble;
    }

    public void setEnsamble(Date ensamble) {
        this.ensamble = ensamble;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

   
    
}
