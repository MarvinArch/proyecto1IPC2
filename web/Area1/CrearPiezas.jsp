<%-- 
    Document   : CrearPiezas
    Created on : 22/08/2021, 21:40:23
    Author     : alpha
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<LINK rel=StyleSheet href="Diseño/Fabrica.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK rel=StyleSheet href="../Diseño/Fabrica.css">
        <title>Area de ensamblaje</title>
    </head>
    <body>
        <i><%@ include file = "AreaFabrica.jsp" %></i>
        <p>
            * En la opcion crear nuevo tipo de pieza podemos crear un nuevo tipo de pieza que no este en el 
            inventario esto por si es necesario crear un nuevo tipo de mueble
        </p>
        <h3>Crear nuevo tipo de Pieza</h3>
        <FORM action="../Agregar" method="POST">
            Nombre de la pieza <input type="text" name="Npieza">
            <input type="submit" value="Eliminar" name="boton" />
        </FORM>
        <h3>Ingresar piezas al Inventario</h3>
        <div>
            <form action="../Agregar" method="POST">
                Tipo de Pieza <SELECT name="tipo">
                <%
                    consultas a1 = new consultas();
                    a1.TipoPieza();
                    ArrayList<pieza> piezaInventario = a1.getTipoPiezas();
                    for (int i = 0; i < piezaInventario.size(); i++) {
                        out.print("<option value='"+piezaInventario.get(i).getNombre()+"'>"+piezaInventario.get(i).getNombre()+"</option>");
                    }
                %>
            
                </SELECT>
                <BR>
                Precio<input type="text" name="precio"><br>
                Cantidad <input type="number" name="cantidad" value="1" min="1"><br>
                <input type="submit" value="Agregar" name="boton" />
            </form>
        </div>
            
    </body>
</html>
