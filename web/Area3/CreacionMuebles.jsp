<%-- 
    Document   : CreacionMuebles
    Created on : 6/09/2021, 00:46:33
    Author     : alpha
--%>

<%@page import="procesos.consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creacion de Muebles</title>
    </head>
    <body>
        <i><%@ include file = "Area3.jsp" %></i>
        <h1 class="centro">Creacion de Muebles y Piezas de Ensamblaje</h1>
        <%
            try {
                if (request.getParameter("a")!=null) {
                    out.print("<h2 class='centro'>Mueble Creado con exito</h2>");
                }else if(request.getParameter("a").equals("b")){
                    out.print("<h2 class='centro'>Los datos Ingresados son invalidos</h2>");
                }
           } catch (Exception e) {
           }
          
                if (request.getParameter("b")!=null) {
                    out.print("<h2 class='centro'>Pieza Creada con exito</h2>");
                }
 
            
        %>
        <p>
            * En la opcion crear nuevo tipo de mueble Podemos definir un nuevo mueble y el precio que tendra en la sala de ventas.<br>
            * Primero deberemos de crear el mueble y despues crearemos sus distintas piezas una a una
        </p>
        
        <div class='insertar'>
             <h3>Crear nuevo tipo de Mueble</h3>
            <form method="POST" action="../Agregar">
                <h3>Ingrese nombre del Nuevo Mueble <input type='text' name='nombreMueble' > </h3>
                <h3>Precio Venta <input type="text" name="PrecioVenta" ></h3>
                <input type="submit" name="NuevoMuebles" value='Crear Mueble'>
                
            </form>
        
        </div>
        <p>
            * En la opcion crear nuevo tipo de pieza Crearemos las piezas necesarias para ensamblar un mueble creado.<br>
            * En este deberemos de definir la cantidad minima de piezas que deben de haber en inventario<br>
            *Tambien se define el nombre de las piezas que lleva y la cantidad necesaria para poder ensamblar el Mueble 
        </p>
        <div class="agregar"><FORM action="../Agregar" method="POST">
                <h3>Crear nuevo tipo de Pieza</h3>
            Tipo de Mueble <SELECT name="tipoM">
                <%
                    consultas a1 = new consultas();
                    a1.TipoPieza("tipomueble");
                    ArrayList<String> mueblexist = a1.getTipoMueble();
                    for (int i = 0; i < mueblexist.size(); i++) {
                        out.print("<option value='"+mueblexist.get(i)+"'>"+mueblexist.get(i)+"</option>");
                    }
                %>
            
            </SELECT><br>
            Nombre de la pieza <input type="text" name="Npieza"><br>
            Cantidad Necesaria para el Mueble <input type="number" name="cantidadN" value="1" min="1"><br>
            Cantidad Minima en Inventario <input type="number" name="cantidadC" value="1" min="1"><br>
            <br><input type="submit" value="Crear" name="CrearPieza" />
        </FORM></div>
    </body>
</html>
