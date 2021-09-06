<%-- 
    Document   : Area2
    Created on : 1/09/2021, 17:09:46
    Author     : alpha
--%>

<%@page import="Objetos.MuebleVendido"%>
<%@page import="Objetos.MuebleVendido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK rel=StyleSheet href="../Diseño/Fabrica.css">
    </head>
    <body>
        <div class="superior">
            <%
                HttpSession sesion= request.getSession();
                if (sesion.getAttribute("user")!=null && sesion.getAttribute("nivel")=="3") {
                    String usuario=sesion.getAttribute("user").toString();
                    out.print("<h5 class='izquierda'>"+usuario+"</h5>");
                    out.print("<a href='../iniciosesion.jsp?cerrar=true'><h5 class='derecha'>Cerrar Sesion</h5></a>");
                }else{
                    response.sendRedirect("../iniciosesion.jsp");
                }
                ArrayList<MuebleVendido> vendidos = sesion.getAttribute("detalle") == null ? null :  (ArrayList)sesion.getAttribute("detalle");
                ArrayList<String> fechas = sesion.getAttribute("fechas") == null ? null :  (ArrayList)sesion.getAttribute("fechas");
            %>
            <br>
            <h1 class="fondoTraslucido">Mi Muebleria</h1><p></p>
            <h3 class="fondoTraslucido">Area Administrativa</h3>
            
        </div>
        
        <div class="barra">
            <a href="CreacionMuebles.jsp" class="redireccion" >Creacion Muebles</a>
            <a href="ReportesVentasTiempo.jsp" class="redireccion" >Reporte de Ventas</a>
            <a href="ReporteGanacias.jsp" class="redireccion">Reporte Ganacias</a>
            <a href="UsuarioMasVentas.jsp" class="redireccion">Mejor Vendedor</a>
            <a href="UsuarioMayorGanancia.jsp" class="redireccion">Vendedor con Mayor Ganacias</a>
            <a href="MuebleMasVendido.jsp" class="redireccion">Mueble mas Vendido</a>
            <a href="MuebleMenosVendido.jsp" class="redireccion">Mueble Menos Vendido</a>
            <a href="CrearModificarUsuarios.jsp" class="redireccion" >Gestion de Usuarios</a>
        </div>
                
    </body>
</html>
