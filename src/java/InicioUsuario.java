/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import procesos.ComprobarUsuario;

/**
 *
 * @author alpha
 */
public class InicioUsuario extends HttpServlet {
    ComprobarUsuario a1 = new ComprobarUsuario();
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
            if (request.getParameter("iniciar")!=null) {
                String Usuario = request.getParameter("usuario");
                String Contraseña=request.getParameter("contra");
                int area= a1.BuscarUsuario(Usuario, Contraseña);
                if (area==1) {
                    
                    response.sendRedirect("AreaFabrica.jsp");
                }else if(area==2){
                    
                    response.sendRedirect("salaventas.jsp");
                }else if (area==3){
                    
                    //response.sendRedirect("");                
                }else{
                out.print("<img src='Diseño/alerta.png'class='imagen'><h3 class= 'alerta'>El usuario y contraseña ingresados no coinciden</h3>");
                }
             }
           if (request.getParameter("cerrar")!=null) {
                   
               }
        
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
