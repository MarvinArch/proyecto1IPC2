/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import procesos.ComprobarUsuario;
import procesos.consultas;

/**
 *
 * @author alpha
 */
public class GestionUsuarios extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GestionUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GestionUsuarios at " + request.getContextPath() + "</h1>");
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
        ComprobarUsuario usr= new ComprobarUsuario();
        consultas cons = new consultas();
        if (request.getParameter("crearUsuario")!=null) {
            try {
                String nombre=request.getParameter("newuser");
                String contraseña=request.getParameter("password");
                int nivel = Integer.parseInt(request.getParameter("tipoM"));
                usr.AgregarUsuario(nombre, contraseña, nivel);
                response.sendRedirect("Area3/CrearModificarUsuarios.jsp?a=a");
            } catch (Exception e) {
                response.sendRedirect("Area3/CrearModificarUsuarios.jsp?a=a");
            }
        }
        if (request.getParameter("Eliminarusr")!=null) {
            try {
                String[] usuarios = request.getParameterValues("Eliminados");
                for (int i = 0; i < usuarios.length; i++) {
                    cons.EliminarPieza(usuarios[i], "usuario", "nombre");
                }
                response.sendRedirect("Area3/CrearModificarUsuarios.jsp?a=a");
            } catch (Exception e) {
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
