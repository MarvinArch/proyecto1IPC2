<%-- 
    Document   : informacionPieza
    Created on : 24/08/2021, 18:01:33
    Author     : alpha
--%>

<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informacion Piezas</title>
        <LINK rel=StyleSheet href="Diseño/Fabrica.css">
    </head>
    <body>
        <i><%@ include file = "AreaFabrica.jsp" %></i>
        <div class="barra">
        <table class="default" >    
        <tr>
            <th>Tipo Mueble</th>
            <th>Pieza</th>
            <%
            consultas a1 = new consultas();
        a1.InforPieza();
        ArrayList<pieza> tipoPiezas=a1.getPiezaInventario();
        
            if (request.getParameter("orden")==null) {
                request.setAttribute("orden", "a");
                out.print("<th><a href='informacionPieza.jsp?orden=b'>Cantidad</a></th>");
                Collections.sort(tipoPiezas, new Comparator<pieza>() {
                @Override
                public int compare(pieza p1, pieza p2) {
                    return new Integer(p2.getCantidad()).compareTo(new Integer(p1.getCantidad()));
                }
                });
            }else{
                request.setAttribute("orden", "b");
                out.print("<th><a href='informacionPieza.jsp'>Cantidad</a></th>");
                Collections.sort(tipoPiezas, new Comparator<pieza>() {
                @Override
                public int compare(pieza p1, pieza p2) {
                    return new Integer(p1.getCantidad()).compareTo(new Integer(p2.getCantidad()));
                }
                });
            }
       
        %>
            
            <th>Estado</th>
        </tr>
        
         
        <%
        for (int i = 0; i < tipoPiezas.size(); i++) {
            out.print("<tr>");
            out.print("<td>"+tipoPiezas.get(i).getMueble()+"</td>");
            out.print("<td>"+tipoPiezas.get(i).getNombre()+"</td>");
            out.print("<td>"+""+tipoPiezas.get(i).getCantidad()+"</td>");
            if (tipoPiezas.get(i).getCantidad()<tipoPiezas.get(i).getMinimo()) {
                out.print("<td>Alerta Inventario</td>");
            }else if (tipoPiezas.get(i).getCantidad()>=tipoPiezas.get(i).getMinimo()) {
                out.print("<td>Existencia Inventario</td>");
            }
            out.print("</tr>");
        }
        
        %>
        </div>
        
    </body>
</html>
