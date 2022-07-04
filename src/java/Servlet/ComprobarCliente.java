/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Objetos.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import procesos.ComprobarUsuario;


/**
 *
 * @author alpha
 * ComprobarExistencia
 */
public class ComprobarCliente extends HttpServlet {

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
            ComprobarUsuario usr = new ComprobarUsuario();
        if (request.getParameter("btnBuscar")!=null) {
            String nit = request.getParameter("nit");
            String[] usuario = usr.BuscarCliente(nit);
            if (usuario.length>1) {
                HttpSession sesion = request.getSession(true);
                ArrayList<Cliente> UsuarioEncontrado = sesion.getAttribute("usuario") == null ? new ArrayList<Cliente>() :  (ArrayList)sesion.getAttribute("usuario");
                sesion.setAttribute("usuario", UsuarioEncontrado);
                UsuarioEncontrado.add(new Cliente(nit,usuario[1],usuario[2]));
                response.sendRedirect("Area2/salaventas.jsp");
                
            }else{
                response.sendRedirect("Area2/salaventas.jsp?er=nt");
            }
            
        }
        if (request.getParameter("btnAgregar")!=null) {
            String nit = request.getParameter("nit");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            usr.AgregarCliente(nit, nombre, direccion);
            response.sendRedirect("ComprobarExistencia?btnBuscar=Buscar&nit="+nit+"");
        }
        
        if (request.getParameter("Cancelar")!=null) {
            response.sendRedirect("Area2/salaventas.jsp?li=si");
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
        processRequest(request, response);
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
