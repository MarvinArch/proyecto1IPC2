<%-- 
    Document   : InformacionMueble
    Created on : 1/09/2021, 02:32:28
    Author     : alpha
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="procesos.consultas"%>
<%@page import="Objetos.mueble"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collections"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informacion Ensambles</title>
    </head>
    <body>
        <i><%@ include file = "AreaFabrica.jsp" %></i>
        <h1 class="centro">Informacion Mueble Ensamblado</h1>
        <div class="informacion">
        <table class="default" >    
        <tr>
        <th>Identificador</th>
        <th>Tipo Mueble</th>
        <th>Usuario Ensamblador</th>
        <%
            consultas a1 = new consultas();
            a1.InfoMueble();
            //array de pieza en el inventario en la base de datos
            ArrayList<mueble> tipoPiezas=a1.getMuebleInventario();
        
            if (request.getParameter("orden")==null) {
                request.setAttribute("orden", "a");
                out.print("<th><a href='InformacionMueble.jsp?orden=b'class='s5'>Fecha Ensamble &#8593; </a></th>");
                //Utilizar metodos comparando los datos dentro del arreglo para ordenarlos ya sea ascendente o desendente
                Collections.sort(tipoPiezas, new Comparator<mueble>() {
                public int compare(mueble o1, mueble o2) {
                return o2.getEnsamble().compareTo(o1.getEnsamble());
                }
                });

            }else{
                request.setAttribute("orden", "b");
                out.print("<th><a href='InformacionMueble.jsp 'class='s5'>Fecha Ensamble &#8595;</a></th>");
                Collections.sort(tipoPiezas, new Comparator<mueble>() {
                public int compare(mueble o1, mueble o2) {
                return o1.getEnsamble().compareTo(o2.getEnsamble());
                }
                });
            }
       
        %>
        <th>Costo Ensamble</th>
        <th>Precio Venta</th>
        </tr>
        <%
        for (int i = 0; i < tipoPiezas.size(); i++) {
            out.print("<tr>");
            out.print("<td>"+tipoPiezas.get(i).getIdentificador()+"</td>");
            out.print("<td>"+tipoPiezas.get(i).getNombre()+"</td>");
            out.print("<td>"+tipoPiezas.get(i).getEnsambladro()+"</td>");
            out.print("<td>"+""+tipoPiezas.get(i).getEnsamble()+"</td>");
            out.print("<td>Q. "+tipoPiezas.get(i).getPrecioEnsamble()+"</td>");
            out.print("<td>Q. "+tipoPiezas.get(i).getPrecioVenta()+"</td>");
            out.print("</tr>");
        }
        
        %>
        </div>
    </body>
</html>
