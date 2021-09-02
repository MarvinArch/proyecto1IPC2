<%-- 
    Document   : RegistrarMueble
    Created on : 31/08/2021, 02:31:12
    Author     : alpha
--%>

<%@page import="Objetos.mueble"%>
<%@page import="java.util.ArrayList"%>
<%@page import="procesos.consultas"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <%
        boolean exito=false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        consultas a2 = new consultas();
        if (request.getParameter("eleccion")!=null) {
                float costo=0;
                a2.TipoPieza("tipomueble");
                a2.infomueble(request.getParameter("eleccion"));
                ArrayList<String> codigo=a2.getCodigo();
                //se recoge un arreglo de codigos
                ArrayList<mueble> muebleInve=a2.getMuebleInventario();
                //se calcula el costo total del ensamblaje del mueble
                for (int i = 0; i < muebleInve.size(); i++) {
                        if (muebleInve.get(i).getNombre().equals(request.getParameter("eleccion"))) {
                                costo=muebleInve.get(i).getPrecioEnsamble();
                            }
                    }
                if (codigo.size()>0) {
                    exito=a2.CrearMueble(session.getAttribute("user").toString(), dtf.format(LocalDateTime.now()).toString(), request.getParameter("eleccion"), costo);
                    }
                
                if (exito==true) {
                    for (int i = 0; i < codigo.size(); i++) {
                        a2.EliminarPieza(codigo.get(i).toString(), "mprima");
                    }
                    out.print("El Mueble creado es "+request.getParameter("eleccion")+"<br>");
                    out.print("El Usuario que ensamblo el mueble es "+session.getAttribute("user"));
                    out.print("<br>Con fecha "+dtf.format(LocalDateTime.now()));
                }
                
                
            }
        %>
    </body>
</html>
