<%-- 
    Document   : VentasDia
    Created on : 5/09/2021, 22:11:13
    Author     : alpha
--%>

<%@page import="procesos.FormatoFecha"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Date"%>
<%@page import="Objetos.MuebleVendido"%>
<%@page import="procesos.consultas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas Del Dia</title>
    </head>
    <body>
        <i><%@ include file = "Area2.jsp" %></i>
        <%
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fecha=dtf.format(LocalDateTime.now());
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaHoy= formato.parse(fecha);
        %>
        <h1 class="centro">Historial de Ventas del Dia <%=FormatoFecha.CambiarFormatoFecha(formato.format(fechaHoy).toString())%></h1>
        <div>
            <table>
                <tr>
                    <th>Vendedor</th>
                    <th>Mueble</th>
                    <th>Precio</th>
                    <th>Nit Cliente</th>
                </tr>
                 <%
                        consultas cons = new consultas();
                        ArrayList<MuebleVendido> vendidos = cons.ArregloVendido();
                        for (int i = 0; i < vendidos.size(); i++) {
                                if (vendidos.get(i).getEnsamble().equals(fechaHoy)) {
                                
                        %>
                        <tr>
                            <td><%=vendidos.get(i).getVendedor()%></td>
                            <td><%=vendidos.get(i).getNombre()%></td>
                            <td><%=vendidos.get(i).getPrecioVenta()%></td>
                            <td><%=vendidos.get(i).getCliente()%></td>
                        </tr>
                        <%
                                    }
                            }
                    %>
            </table>
        </div>
    </body>
</html>
