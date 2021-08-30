<%-- 
    Document   : EnsamblarMueble
    Created on : 28/08/2021, 22:59:09
    Author     : alpha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <LINK rel=StyleSheet href="Diseño/Fabrica.css">
    </head>
    <body>
        <i><%@ include file = "AreaFabrica.jsp" %></i>
        <h1>Empezamos el ensamblaje</h1>
        <form method="POST" accion="EnsamblarMueble.jsp">
        <%
            consultas a1 = new consultas();
            a1.TipoPieza("tipomueble");
            ArrayList<String> tipoMueble= a1.getTipoMueble();
            int num=tipoMueble.size();
            String[] funcion= new String[num];
            for (int i = 0; i < num; i++) {
                    funcion[i]=tipoMueble.get(i).toString();
                }
            for (int i = 0; i < num; i++) {
                    ArrayList<String> lineaTexto=a1.infomueble(funcion[i]);
                    for (int j = 0; j < lineaTexto.size(); j++) {
                            out.print(lineaTexto.get(j).toString());
                        }
                    out.print("<br>");
                }
            
           
        %>
        <i><%@ include file = "prueba.jsp" %></i>

        
        </form>
    </body>
</html>
