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
        <div class="informacion">
        <table class="default" >    
        <tr>
        <th>Tipo Mueble</th>
        <th>Pieza</th>
            <!-- Definir la columna de cantidad -->    
        <%
            consultas a1 = new consultas();
            a1.InforPieza();
            //array de pieza en el inventario en la base de datos
            ArrayList<pieza> tipoPiezas=a1.getPiezaInventario();
        
            if (request.getParameter("orden")==null) {
                request.setAttribute("orden", "a");
                out.print("<th><a href='informacionPieza.jsp?orden=b'class='s5'>Cantidad &#8593; </a></th>");
                //Utilizar metodos comparando los datos dentro del arreglo para ordenarlos ya sea ascendente o desendente
                Collections.sort(tipoPiezas, new Comparator<pieza>() {
                @Override
                public int compare(pieza p1, pieza p2) {
                    return new Integer(p2.getCantidad()).compareTo(new Integer(p1.getCantidad()));
                }
                });
            }else{
                request.setAttribute("orden", "b");
                out.print("<th><a href='informacionPieza.jsp'class='s5'>Cantidad &#8595;</a></th>");
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
        
        <!-- Imprimir el con la informacion de cada pieza existente en el invetario --> 
        <%
        for (int i = 0; i < tipoPiezas.size(); i++) {
            out.print("<tr>");
            out.print("<td>"+tipoPiezas.get(i).getMueble()+"</td>");
            out.print("<td>"+tipoPiezas.get(i).getNombre()+"</td>");
            out.print("<td>"+""+tipoPiezas.get(i).getCantidad()+"</td>");
            if (tipoPiezas.get(i).getCantidad()<tipoPiezas.get(i).getMinimo()) {
                out.print("<td>&#9888; Alerta Inventario</td>");
            }else if (tipoPiezas.get(i).getCantidad()>=tipoPiezas.get(i).getMinimo()) {
                out.print("<td>Existencia Inventario</td>");
            }
            out.print("</tr>");
        }
        
        %>
        </div>
        
    </body>
</html>
