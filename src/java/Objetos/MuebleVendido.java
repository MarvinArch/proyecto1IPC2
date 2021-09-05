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
public class MuebleVendido extends mueble {
    protected String vendedor;
    protected int factura;
    protected String cliente;

    public MuebleVendido() {
    }

    public MuebleVendido(String identificador, String vendedor, Date venta, String nombre, float costo, float precio, String cliente, int factura) {
        this.identificador=identificador;
        this.vendedor = vendedor;
        this.ensamble = venta;
        this.nombre = nombre;
        this.precioEnsamble = costo;
        this.precioVenta = precio;
        this.factura = factura;
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

     
}
