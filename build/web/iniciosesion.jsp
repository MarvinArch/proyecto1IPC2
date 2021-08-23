<%-- 
    Document   : iniciosesion
    Created on : 15/08/2021, 21:41:44
    Author     : alpha
--%>

<%@page import="procesos.ComprobarUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio Sesion</title>
        <LINK rel=StyleSheet href="Diseño/sesion.css">
    </head>
    <body>
        <h1 class="titulos">Tienda de Mueble Nombre de la tienda</h1>
        
        
        <form action="iniciosesion.jsp" method="POST" class="cuadro">
            <div class="centro">
                <h2>Iniciar de sesion</h2>
                Usuario&nbsp&nbsp&nbsp<input type="text" name="usuario"><br><br>
                Contraseña <input type="password" name="contra"><br><br><br>
                <input type="submit" value="Inicio" name="iniciar">
                <%
            ComprobarUsuario autorizado= new ComprobarUsuario();
            if (request.getParameter("iniciar")!=null) {
                String Usuario = request.getParameter("usuario");
                String Contraseña=request.getParameter("contra");
                int area= autorizado.BuscarUsuario(Usuario, Contraseña);
                HttpSession sesion = request.getSession();
                sesion.setAttribute("user", Usuario);
                if (area==1) {
                    sesion.setAttribute("nivel", "1");
                    response.sendRedirect("AreaFabrica.jsp");
                }else if(area==2){
                    sesion.setAttribute("area", 2);
                    response.sendRedirect("salaventas.jsp");
                }else if (area==3){
                    sesion.setAttribute("area", 3);
                    //response.sendRedirect("");                
                }else{
                out.print("<img src='Diseño/alerta.png'class='imagen'><h3 class= 'alerta'>El usuario y contraseña ingresados no coinciden</h3>");
                }
             }
           if (request.getParameter("cerrar")!=null) {
                   session.invalidate();
               }
        %>
            </div>
        </form>
    </body>
</html>
