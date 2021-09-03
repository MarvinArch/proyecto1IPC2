<%-- 
    Document   : salaventas
    Created on : 15/08/2021, 23:25:21
    Author     : alpha
--%>

<%@page import="Objetos.mueble"%>
<%@page import="Objetos.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<%
    HttpSession sesion2 = request.getSession(true);
    ArrayList<Cliente> UsuarioEncontrado = sesion2.getAttribute("usuario") == null ? null :  (ArrayList)sesion2.getAttribute("usuario");
    ArrayList<mueble> muebleCarrito = sesion2.getAttribute("carrito") == null ? null :  (ArrayList)sesion2.getAttribute("carrito");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK rel=StyleSheet href="../Diseño/Tienda.css">
        <title>Sala de ventas</title>
    </head>
    <body>
        <%
            String nit ="";
            String nombre ="";
            String direccion ="";
            String deshabilitar="disabled='true'";
            String deshabilitar2="";
            String total="0";
            
            try {
                for (int i = 0; i < UsuarioEncontrado.size(); i++) {
                nit= UsuarioEncontrado.get(i).getNit();
                nombre=UsuarioEncontrado.get(i).getNombre();
                direccion=UsuarioEncontrado.get(i).getDireccion();
                deshabilitar2="disabled='true'";
                }
            } catch (Exception e) {
            }
            if (sesion2.getAttribute("total")!=null) {
                    total=session.getAttribute("total").toString();
                }
            if (request.getParameter("li")!=null) {
                try {
                    if (UsuarioEncontrado.size()>0) UsuarioEncontrado.clear();
                    } catch (Exception e) {
                    }
                try {
                        if (muebleCarrito.size()>0) muebleCarrito.clear();
                    } catch (Exception e) {
                    }
                    sesion2.setAttribute("total", "0");
                response.sendRedirect("salaventas.jsp");
            }
        %>
        <i><%@ include file = "Area2.jsp" %></i>
        <h1>sala de ventas</h1>
        <div class="atras">
            <div class="izquierdabody">
                <form method="POST" action="../ComprobarExistencia">
                        <h3 class="izquierdaform">Ingrese Nit Cliente </h3><input class="derechaform" type="text" name="nit" value="<%=nit%>" <%=deshabilitar2%> >
                    <% 
                        if (request.getParameter("er")!=null) {
                                deshabilitar="";
                            }
                        if (request.getParameter("cliente")==null) {
                    %>
                    <div class="centro"><input type="submit" value="Buscar" name="btnBuscar" <%=deshabilitar2%>></div>
                    
                    <%
                        }
                        if (request.getParameter("er")!=null) {
                        %><br>El Nit ingresado no ha sido encontrado por favor registre como nuevo cliente<br><%   
                        }
                    %>
                    <h3 class="izquierdaform">Nombre Cliente</h3> <input class="derechaform" type="text" name="nombre" <%=deshabilitar%> value="<%=nombre%>">
                    <br><br><h3 class="izquierdaform">Direccion</h3> <input class="derechaform" type="text" name="direccion" <%=deshabilitar%> value="<%=direccion%>">
                    <br><div class="centro" ><input type="submit" value="Registrar" <%=deshabilitar%> name="btnAgregar"></div>
                    
                </form>
                    <form method="POST" action="../VentaMueble">
                        <br><h4 class="izquierdaform">Total</h4><h4 >Q.<%=total%></h4>
                        <div class="centro">
                        <input type="submit" value="Cancelar Compra" name="Cancelar">
                        <input type="submit" value="Terminar Compra" name="Pagar">
                        </div>
            </div>
            <div class="derechabody">
                <h3 class="izquierdaform" >Ingrese Codigo Producto</h3><input class="derechaform" type="Text" name="codigo" >
                <br><div class="centro"><input type="submit" name="Buscar producto" value="Agregar a Factura">
                <table >
                    <tr>
                        <th>Cantidad</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Eliminar</th>
                    </tr>
                
                <%
                    
                    try {
                        for (int i = 0; i < muebleCarrito.size(); i++) { 
                %>
                <tr><td><%=muebleCarrito.get(i).getExistencia()+1%></td>
                    <td><%=muebleCarrito.get(i).getNombre()%></td>
                    <td><%=muebleCarrito.get(i).getPrecioVenta()%></td>
                    <td><button action="submit" name="Eliminar" value="<%=muebleCarrito.get(i).getIdentificador()%>">Eliminar</button></td></tr> 
                    
                <%
                    } 
                        } catch (Exception e) {
                        }
                    
                %>
                </table>
                    </form>
            </div>
            
        </div>
    </body>
</html>
