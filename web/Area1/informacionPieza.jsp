<%-- 
    Document   : informacionPieza
    Created on : 24/08/2021, 18:01:33
    Author     : alpha
--%>

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
        <table class="default" >    
        <tr>
            <th>Tipo Mueble</th>
            <th>Pieza</th>
            <th>Cantidad</th>
            <th>Estado</th>
        </tr>
    </body>
</html>
