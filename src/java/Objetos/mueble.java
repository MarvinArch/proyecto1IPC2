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
    private String identificador;
    private String nombre;
    private String ensambladro;
    private float precioVenta;
    private float precioEnsamble;
    private Date ensamble;
    private int existencia;

    public mueble(String identificador, String nombre, String ensambladro, float precioVenta, float precioEnsamble, Date ensamble) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.ensambladro = ensambladro;
        this.precioVenta = precioVenta;
        this.precioEnsamble = precioEnsamble;
        this.ensamble = ensamble;
    }

    public mueble() {
    }
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEnsambladro() {
        return ensambladro;
    }

    public void setEnsambladro(String ensambladro) {
        this.ensambladro = ensambladro;
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
