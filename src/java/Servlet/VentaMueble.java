/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Objetos.Cliente;
import Objetos.mueble;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import procesos.ComprobarUsuario;
import procesos.consultas;

/**
 *
 * @author alpha
 */
public class VentaMueble extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }
        
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);
        ArrayList<mueble> muebleCarrito = sesion.getAttribute("carrito") == null ? new ArrayList<mueble>() :  (ArrayList)sesion.getAttribute("carrito");
        float total=0;
        if (request.getParameter("Cancelar")!=null) {
            response.sendRedirect("Area2/salaventas.jsp?li=si");
        }
        if (request.getParameter("Buscar producto")!=null) {
            mueble encontrado;
            boolean duplicado=false;
            
            consultas buscar= new consultas();
            buscar.InfoMueble(request.getParameter("codigo"));
            if (buscar.getMueble()!=null) {
                encontrado = buscar.getMueble();
                sesion.setAttribute("carrito", muebleCarrito);
                for (int i = 0; i < muebleCarrito.size(); i++) {
                    if (muebleCarrito.get(i).getIdentificador().equals(encontrado.getIdentificador())) {
                        duplicado=true;
                    }
                }
                if (duplicado==false) {
                    muebleCarrito.add(encontrado);
                }
                for (int i = 0; i < muebleCarrito.size(); i++) {
                    total+=muebleCarrito.get(i).getPrecioVenta();
                }
                sesion.setAttribute("total", ""+total+"");
                response.sendRedirect("Area2/salaventas.jsp");
                
            }else{
                response.sendRedirect("Area2/salaventas.jsp?co=nt");
            }
        }
        if (request.getParameter("Eliminar")!=null) {
            for (int i = 0; i < muebleCarrito.size(); i++) {
                String codigo=request.getParameter("Eliminar");
                if (codigo.equals(muebleCarrito.get(i).getIdentificador())) {
                    muebleCarrito.remove(muebleCarrito.get(i));
                }
            }
            for (int i = 0; i < muebleCarrito.size(); i++) {
                    total+=muebleCarrito.get(i).getPrecioVenta();
                }
                sesion.setAttribute("total", ""+total+"");
            response.sendRedirect("Area2/salaventas.jsp");
        }
        if (request.getParameter("Pagar")!=null) {
            consultas eliminar = new consultas();
            try {
                ArrayList<Cliente> cliente = (ArrayList)sesion.getAttribute("usuario");
                String cliente1= cliente.get(0).getNit();
                String Vendedor= sesion.getAttribute("user").toString();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fecha=""+dtf.format(LocalDateTime.now());
                ComprobarUsuario factura = new ComprobarUsuario();
                String precio= sesion.getAttribute("total").toString();
                factura.NoFactura(Vendedor, precio, cliente1);
                int fac= factura.BuscarFactura();
                for (int i = 0; i < muebleCarrito.size(); i++) {
                    eliminar.EliminarPieza(muebleCarrito.get(i).getIdentificador(), "mueble_ensamblado", "identificador");
                }
                eliminar.RegistrarVenta(muebleCarrito, cliente1, Vendedor, fecha, fac);
                response.sendRedirect("Area2/salaventas.jsp?a=a"); 
            } catch (Exception e) {
                response.sendRedirect("Area2/salaventas.jsp?er=40");
            }
            
        }
    }

    
}
