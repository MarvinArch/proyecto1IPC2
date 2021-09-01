<%-- 
    Document   : EnsamblarMueble
    Created on : 28/08/2021, 22:59:09
    Author     : alpha
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Objetos.mueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ensamblar Mueble</title>
        <LINK rel=StyleSheet href="Diseño/Fabrica.css">
    </head>
    <body>
        <i><%@ include file = "AreaFabrica.jsp" %></i>
        <h1 class="centro">Area de Ensamble</h1>
        <i><%@ include file = "RegistrarMueble.jsp" %></i>
        <form method="POST" accion="RegistrarMueble.jsp">
        <%
            consultas a1 = new consultas();
            a1.TipoPieza("tipomueble");
            ArrayList<String> tipoMueble= a1.getTipoMueble();
            ArrayList<mueble> muebleInventario= a1.getMuebleInventario();
            int num=tipoMueble.size();
            String[] funcion= new String[num];
            for (int i = 0; i < num; i++) {
                    funcion[i]=tipoMueble.get(i).toString();
                }
            for (int i = 0; i < num; i++) {
                    out.print("<div class='ensamble'>");
                    ArrayList<String> lineaTexto=a1.infomueble(funcion[i]);
                    for (int j = 0; j < lineaTexto.size(); j++) {
                            out.print(lineaTexto.get(j).toString());
                            if (j==0) {
                                   out.print(" tiene un precio de venta de Q."+muebleInventario.get(i).getPrecioVenta()+"<br>");
                                }
                        }
                    out.print("El costo de ensamble del mueble es de Q. "+muebleInventario.get(i).getPrecioEnsamble()+"<br>");
                    out.print("<div class='centro'><button type='submit' name ='eleccion' value='"+funcion[i]+"' class='modificar'>Ensamblar Mueble</button></div>");
                    out.print("</div>");
                    out.print("<br>");
                }
            
           
        %>
        

        
        </form>
        
    </body>
</html>
