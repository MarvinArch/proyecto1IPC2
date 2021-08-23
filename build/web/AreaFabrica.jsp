<%-- 
    Document   : AreaFabrica
    Created on : 15/08/2021, 21:53:22
    Author     : alpha
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="Objetos.pieza"%>
<%@page import="procesos.consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Area de Ensamblaje</title>
        <LINK rel=StyleSheet href="Diseño/Fabrica.css">
    </head>
    <body>
        <div class="superior">
            <%
                HttpSession sesion= request.getSession();
                if (sesion.getAttribute("user")!=null && sesion.getAttribute("nivel")=="1") {
                    String usuario=sesion.getAttribute("user").toString();
                    out.print("<h5 class='izquierda'>"+usuario+"</h5>");
                    out.print("<a href='iniciosesion.jsp?cerrar=true'><h5 class='derecha'>Cerrar Sesion</h5></a>");
                }else{
                    response.sendRedirect("iniciosesion.jsp");
                }
            %>
            <br>
            <h1 class="fondoTraslucido">Mi Muebleria</h1><p></p>
            <h3 class="fondoTraslucido">Area de Ensamblaje</h3>
            
        </div>
        
        <div class="barra">
            <a href="Area1/CrearPiezas.jsp" class="redireccion" >Agregar Piezas</a>
            <a href="" class="redireccion">Ensamblar Mueble</a>
            <a href="" class="redireccion">Modificar/eliminar piezas</a>
            <a href="" class="redireccion">Informacion Piezas</a>
            <a href="" class="redireccion">Informacion Ensambles</a>   
        </div>
                
    </body>
</html>
