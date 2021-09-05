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
public class muebleEnsamblado extends mueble{
    private String ensambladro;
    
    public muebleEnsamblado(String identificador, String nombre, String ensambladro, float precioVenta, float precioEnsamble, Date ensamble) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.ensambladro = ensambladro;
        this.precioVenta = precioVenta;
        this.precioEnsamble = precioEnsamble;
        this.ensamble = ensamble;
    }

    public muebleEnsamblado() {
    }
    
    public muebleEnsamblado(String nombre, float precioVenta, float precioEnsamble) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.precioEnsamble = precioEnsamble;
    }

    public String getEnsambladro() {
        return ensambladro;
    }

    public void setEnsambladro(String ensambladro) {
        this.ensambladro = ensambladro;
    }
    
}
