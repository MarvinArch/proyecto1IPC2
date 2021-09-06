<%-- 
    Document   : Area2
    Created on : 1/09/2021, 17:09:46
    Author     : alpha
--%>

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
                if (sesion.getAttribute("user")!=null && sesion.getAttribute("nivel")=="2") {
                    String usuario=sesion.getAttribute("user").toString();
                    out.print("<h5 class='izquierda'>"+usuario+"</h5>");
                    out.print("<a href='../iniciosesion.jsp?cerrar=true'><h5 class='derecha'>Cerrar Sesion</h5></a>");
                }else{
                    response.sendRedirect("../iniciosesion.jsp");
                }
            %>
            <br>
            <h1 class="fondoTraslucido">Mi Muebleria</h1><p></p>
            <h3 class="fondoTraslucido">Area de Punto de Venta</h3>
            
        </div>
        
        <div class="barra">
            <a href="salaventas.jsp" class="redireccion" >Sala Venta</a>
            <a href="" class="redireccion" >Devoluciones</a>
            <a href="" class="redireccion">Devoluciones cliente</a>
            <a href="ConsultaCliente.jsp" class="redireccion">Compras cliente</a>
            <a href="MueblesDisponibles.jsp" class="redireccion">Inventario de Muebles</a>
            <a href="DetalleFactura.jsp" class="redireccion">Detalles factura</a>
            <a href="VentasDia.jsp" class="redireccion">Ventas del dia</a>   
        </div>
                
    </body>
</html>
