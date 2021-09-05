<%-- 
    Document   : EnsamblarMueble
    Created on : 28/08/2021, 22:59:09
    Author     : alpha
--%>

<%@page import="procesos.CrearInfoEnsamble"%>
<%@page import="Objetos.muebleEnsamblado"%>
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
            consultas a1= new consultas();
            CrearInfoEnsamble info=new CrearInfoEnsamble();
            CrearInfoEnsamble info3=new CrearInfoEnsamble();
            ArrayList<String> tipoMueble= info3.TipoMueble("tipomueble");
            for (int i = 0; i < tipoMueble.size(); i++) {
                    out.print("<div class='ensamble'>");
                    ArrayList<String> lineaTexto=info.infoMueble(tipoMueble.get(i));
                    ArrayList<muebleEnsamblado> muebleInventario= info.getMuebleInventario();
                    for (int j = 0; j < lineaTexto.size(); j++) {
                            out.print(lineaTexto.get(j).toString());
                            if (j==0) {
                                   out.print(" tiene un precio de venta de Q."+muebleInventario.get(i).getPrecioVenta()+"<br>");
                                }
                        }
                    out.print("El costo de ensamble del mueble es de Q. "+muebleInventario.get(i).getPrecioEnsamble()+"<br>");
                    out.print("<div class='centro'><button type='submit' name ='eleccion' value='"+tipoMueble.get(i).toString()+"' class='modificar'>Ensamblar Mueble</button></div>");
                    out.print("</div>");
                    out.print("<br>");
                }
            
           
        %>
        

        
        </form>
        
    </body>
</html>
