<%-- 
    Document   : CrearPiezas
    Created on : 22/08/2021, 21:40:23
    Author     : alpha
--%>
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
        <FORM action="../Agregar" method="POST">
            Nombre de la pieza <input type="text" name="Npieza">
            <input type="submit" value="Crear" name="crear" />
        </FORM>
        <h3>Ingresar piezas</h3>
        <form action="action" method="POST">
            <%
                
            %>
            
        </form>
            
    </body>
</html>
