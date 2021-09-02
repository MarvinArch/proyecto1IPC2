<%-- 
    Document   : salaventas
    Created on : 15/08/2021, 23:25:21
    Author     : alpha
--%>

<%@page import="Objetos.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<%
    HttpSession sesion2 = request.getSession(true);
    ArrayList<Cliente> UsuarioEncontrado = sesion2.getAttribute("usuario") == null ? null :  (ArrayList)sesion2.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sala de ventas</title>
    </head>
    <body>
        <%
            String nit ="";
            String nombre ="";
            String direccion ="";
            String deshabilitar="disabled='true'";
            String deshabilitar2="";
            try {
                for (int i = 0; i < UsuarioEncontrado.size(); i++) {
                nit= UsuarioEncontrado.get(i).getNit();
                nombre=UsuarioEncontrado.get(i).getNombre();
                direccion=UsuarioEncontrado.get(i).getDireccion();
                deshabilitar2="disabled='true'";
                }
            } catch (Exception e) {
            }
            if (request.getParameter("li")!=null) {
                UsuarioEncontrado.clear();
                response.sendRedirect("salaventas.jsp");
            }
        %>
        <i><%@ include file = "Area2.jsp" %></i>
        <h1>sala de ventas</h1>
        <div class="general">
            <div>
                <form method="POST" action="../ComprobarExistencia">
                    Ingrese Nit Cliente <input type="text" name="nit" value="<%=nit%>" <%=deshabilitar2%> >
                    <% 
                        if (request.getParameter("er")!=null) {
                                deshabilitar="";
                            }
                        if (request.getParameter("cliente")==null) {
                    %>
                    <input type="submit" value="Buscar" name="btnBuscar" <%=deshabilitar2%>>
                    
                    <%
                        }
                        if (request.getParameter("er")!=null) {
                        %><br>El Nit de usuario ingresado no ha sido encontrado<%   
                        }
                    %>
                    <br>Nombre Cliente <input type="text" name="nombre" <%=deshabilitar%> value="<%=nombre%>">
                    <br>Direccion <input type="text" name="direccion" <%=deshabilitar%> value="<%=direccion%>">
                    <br><input type="submit" value="Registrar" <%=deshabilitar%> name="btnAgregar">
                    <br><input type="submit" value="CAncelar Compra" name="Cancelar">
                    
                </form>
            </div>
            
        </div>
    </body>
</html>
