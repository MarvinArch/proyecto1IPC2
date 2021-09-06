<%-- 
    Document   : CrearPiezas
    Created on : 22/08/2021, 21:40:23
    Author     : alpha
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="procesos.consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<LINK rel=StyleSheet href="Diseño/Fabrica.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK rel=StyleSheet href="../Diseño/Fabrica.css">
        <title>Area de ensamblaje</title>
    </head>
    <body>
        <i><%@ include file = "AreaFabrica.jsp" %></i>
        <div class="centro"><h3>Ingresar piezas al Inventario</h3></div>
        <div class='agregar'>
            <form action="../Agregar" method="POST">
                Tipo de Pieza <SELECT name="tipo">
                <%
                    consultas a1 = new consultas();
                    a1.TipoPieza("pieza");
                    ArrayList<String> piezaInventario = a1.getTipoMueble();
                    for (int i = 0; i < piezaInventario.size(); i++) {
                        out.print("<option value='"+piezaInventario.get(i).toString()+"'>"+piezaInventario.get(i).toString()+"</option>");
                    }
                %>
            
                </SELECT>
                <BR>
                Precio<input type="text" name="precio"><br>
                Cantidad <input type="number" name="cantidad" value="1" min="1"><br>
                <input type="submit" value="Agregar" name="InsertarPiezas" />
            </form>
        </div>
            
    </body>
</html>
