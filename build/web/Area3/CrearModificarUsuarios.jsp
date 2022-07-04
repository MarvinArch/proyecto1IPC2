<%-- 
    Document   : CrearModificarUsuarios
    Created on : 6/09/2021, 10:15:29
    Author     : alpha
--%>

<%@page import="procesos.ComprobarUsuario"%>
<%@page import="Objetos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestio De Usuarios</title>
    </head>
    <body>
        <i><%@ include file = "Area3.jsp" %></i>
        <div class="izquierda izquierdabody">
                <h4>Nivel 1 : Area de Fabrica</h4>
                <h4>Nivel 2 : Area de Ventas</h4>
                <h4>Nivel 1 : Area de Administracion</h4>
            </div>
        <div class="agregar">
            <form method="POST" action="../GestionUsuarios">
                <h3>Nombre de Usuario </h3>
                <input type="text" name='newuser'>
                <h3>Contraseña </h3>
                <input type="text" name='password'><br>
                Nivel de Usuario <SELECT name="tipoM">
                    <option value='1'>1</option><option value='2'>2</option><option value='3'>3</option>
            </SELECT><br>
            <input type="submit" name='crearUsuario' value="Crear Usuario">
            </form>
        </div>
        <div><form method="POST" action="../GestionUsuarios">
            <table>
                <tr>
                    <th>Usuario</th>
                    <th>Area Acceso</th>
                    <th>Seleccionar</th>
                </tr>
                <%
                ComprobarUsuario usr= new ComprobarUsuario();
                ArrayList<Usuario> usr2= usr.Usuarios();
                for (int i = 0; i < usr2.size(); i++) {    
                %>
                <tr>
                    <td><%=usr2.get(i).getUsuario()%></td>
                    <td><%=usr2.get(i).getNivel()%></td>
                    <td><input type="checkbox" name="Eliminados" value="<%=usr2.get(i).getUsuario()%>"></td>
                </tr>
                <%
                }
                %>
            </table>
            <div class='centro'><input type="submit" name='Eliminarusr' value="Eliminar"></div><br><br><br>
        </form></div>
    </body>
</html>
