<%-- 
    Document   : ModificarEliminar
    Created on : 23/08/2021, 20:22:47
    Author     : alpha
--%>


<%@page import="Servlet.Eliminar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK rel=StyleSheet href="Diseño/Fabrica.css">
        <title>Modificar/Eliminar</title>
    </head>
    
    <body>
        <i><%@ include file = "AreaFabrica.jsp" %></i>
        <h1>Modificar/Eliminar</h1>
        <form method="POST" action="../Eliminar">
            <table class="default" >
    
            <tr>
            <th>Nombre</th>
            <th>Codigo</th>
            <th>Precio</th>
            <th>Seleccionar</th>
            </tr> 
        
            <%
            consultas n1= new consultas();
            n1.Pieza();
            ArrayList<pieza> piezaInventario = n1.getPiezaInventario();
            
            for (int i = 0; i < piezaInventario.size(); i++) {
                out.print("<tr>");
                out.print("<td>"+piezaInventario.get(i).getNombre()+"</td>");
                out.print("<td>"+piezaInventario.get(i).getCodigo()+"</td>");
                out.print("<td>"+"Q. "+piezaInventario.get(i).getPrecio()+"</td>");
                out.print("<td><input type='checkbox' name='eleccion' value='"+piezaInventario.get(i).getCodigo()+"'></td>");
                out.print("</tr>");
            }
            %>
            </table>
            <input type="submit" name="Modificar" value="Eliminar">
            <input type="submit" name="Modificar" value="Modificar">
            
           
        </form>
        
    </body>
</html>
