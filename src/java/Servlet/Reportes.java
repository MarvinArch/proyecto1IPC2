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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import procesos.consultas;

/**
 *
 * @author alpha
 */
@WebServlet(name = "Reportes", urlPatterns = {"/Reportes"})
public class Reportes extends HttpServlet {

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
            out.println("<title>Servlet Reportes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Reportes at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("reportetiempo")!=null) {
            consultas cons = new consultas();
            try {
                ArrayList<MuebleVendido> vendidos = sesion.getAttribute("detalle") == null ? new ArrayList<MuebleVendido>() :  (ArrayList)sesion.getAttribute("detalle");
                ArrayList<String> fechas = sesion.getAttribute("fechas") == null ? new ArrayList<String>() :  (ArrayList)sesion.getAttribute("fechas");
                vendidos.clear();
                fechas.clear();
                ArrayList<MuebleVendido> total = cons.ArregloVendido();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fechainicial= formato.parse(request.getParameter("inicio"));
                Date fechafinal= formato.parse(request.getParameter("final"));
                for (int i = 0; i < total.size(); i++) {
                    if ((total.get(i).getEnsamble().compareTo(fechainicial)>=0) && (total.get(i).getEnsamble().compareTo(fechafinal)<=0)) {
                        vendidos.add(total.get(i));
                    }
                }
                fechas.add(request.getParameter("inicio"));
                fechas.add(request.getParameter("final"));
                sesion.setAttribute("fechas", fechas);
                sesion.setAttribute("detalle", vendidos);
                if (request.getParameter("c")!=null) {
                    response.sendRedirect("Area3/ReporteGanacias.jsp?a=a");
                }else{
                    response.sendRedirect("Area3/ReportesVentasTiempo.jsp?a=a");
                }
                
                
            } catch (Exception e) {
                if (request.getParameter("c")!=null) {
                    response.sendRedirect("Area3/ReporteGanacias.jsp?a=b");
                }else{
                    response.sendRedirect("Area3/ReportesVentasTiempo.jsp?a=b");
                }
                
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
