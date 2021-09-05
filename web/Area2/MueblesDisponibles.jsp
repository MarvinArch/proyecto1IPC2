<%-- 
    Document   : MueblesDisponibles
    Created on : 1/09/2021, 17:37:43
    Author     : alpha
--%>

<%@page import="Objetos.muebleEnsamblado"%>
<%@page import="procesos.consultas"%>
<%@page import="procesos.CrearOrdenMuebles"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Objetos.mueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventario de Muebles</title>
    </head>
    <body>
        
        <i><%@ include file = "Area2.jsp" %></i>
        <div class="informacion"><table>
                <tr>
                    <%
                        if (request.getParameter("or")!=null) {
                            out.print("<th><a href='MueblesDisponibles.jsp' class='s5'>Existencia &#8595;</a></th>");
                        }else{
                            out.print("<th><a href='MueblesDisponibles.jsp?or=ad' class='s5'>Existencia &#8593;</a></th>");
                        }
                    %>
                    <th>Nombre Mueble</th>
                    <th>Precio</th>
                    
                </tr>
        <%
            CrearOrdenMuebles orden = new CrearOrdenMuebles();
            if (request.getParameter("or")!=null) {
                orden.OrdenMuebles(1);
            }else{
                orden.OrdenMuebles(2);
            }
            ArrayList<mueble> mueble3= orden.getMueble3();
            for (int i = 0; i < mueble3.size(); i++) {
                out.print("<tr><td>"+mueble3.get(i).getExistencia() +"</td><td>"+mueble3.get(i).getNombre() +"</td><td>"+mueble3.get(i).getPrecioVenta() +"</td></tr>");
            }
        %>
        </table></div>
        <br><br>
        <div>
            <table>
                <tr>
                    <th>Codigo Producto</th>
                    <th>Tipo Mueble</th>
                    <th>Precio</th>
                </tr>
                <%
                    consultas cons = new consultas();
                    cons.InfoMueble("not");
                    ArrayList<muebleEnsamblado> inventario= cons.getMuebleInventario();
                    for (int i = 0; i < inventario.size(); i++) {
                %>
                        <tr>
                            <td><%=inventario.get(i).getIdentificador()%></td>
                            <td><%=inventario.get(i).getNombre()%></td>
                            <td><%=inventario.get(i).getPrecioVenta()%></td>
                        </tr>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
