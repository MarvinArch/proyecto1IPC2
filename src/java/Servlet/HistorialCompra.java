/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Objetos.Cliente;
import Objetos.MuebleVendido;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import procesos.ComprobarUsuario;
import procesos.FormatoFecha;
import procesos.consultas;

/**
 *
 * @author alpha
 */
public class HistorialCompra extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String respuesta;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HistorialCompra</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HistorialCompra at " + request.getContextPath() + "</h1>"+request.getParameter("inicial"));
            out.println("</body>");
            out.println("</html>");
        }
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
        consultas cons = new consultas();
        ComprobarUsuario cp= new ComprobarUsuario();
        ArrayList<MuebleVendido> total = cons.ArregloVendido();
        HttpSession sesion = request.getSession(true);
        ArrayList<MuebleVendido> vendidos = sesion.getAttribute("history") == null ? new ArrayList<MuebleVendido>() :  (ArrayList)sesion.getAttribute("history");
        ArrayList<Cliente> cliente = sesion.getAttribute("clientebusca") == null ? new ArrayList<Cliente>() :  (ArrayList)sesion.getAttribute("clientebusca");
        cliente.clear();
                vendidos.clear();        
            if (request.getParameter("buscar")!=null) {
            try {
                String usuario=request.getParameter("usuario");
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date feInicial= formato.parse(request.getParameter("inicial").toString());
                Date feFinal= formato.parse(request.getParameter("final").toString());
                for (int i = 0; i < total.size(); i++) {
                  if (total.get(i).getCliente().equals(usuario) && total.get(i).getEnsamble().after(feInicial) && (total.get(i).getEnsamble().before(feFinal)|total.get(i).getEnsamble().equals(feFinal))) {
                        vendidos.add(total.get(i));
                    }
                }
                String[] info=cp.BuscarCliente(usuario);
                cliente.add(new Cliente(info[0],info[1],info[2]));
                sesion.setAttribute("clientebusca", cliente);
                sesion.setAttribute("history", vendidos);
                if (vendidos.size()>0) {
                    response.sendRedirect("Area2/ConsultaCliente.jsp?a=a");
                }else{
                    response.sendRedirect("Area2/ConsultaCliente.jsp?not=1");
                }
                
            
            } catch (Exception e) {
                response.sendRedirect("Area2/ConsultaCliente.jsp?er=20");
            }   
        }
        if (request.getParameter("BuscarFactura")!=null) {
            try {
                String factura=request.getParameter("factura");
                for (int i = 0; i < total.size(); i++) {
                    if (total.get(i).getFactura()==Integer.parseInt(factura)) {
                        vendidos.add(total.get(i));
                    }
                }
                String[] info=cp.BuscarCliente(vendidos.get(0).getCliente());
                cliente.add(new Cliente(info[0],info[1],info[2]));
                sesion.setAttribute("cliente", cliente);
                sesion.setAttribute("detalle", vendidos);
                response.sendRedirect("Area2/DetalleFactura.jsp?a=a");
            } catch (Exception e) {
                response.sendRedirect("Area2/DetalleFactura.jsp?a=b");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
