<%-- 
    Document   : ModificarEliminar
    Created on : 23/08/2021, 20:22:47
    Author     : alpha
--%>


<%@page import="Objetos.pieza"%>
<%@page import="procesos.consultas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Servlet.Eliminar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK rel=StyleSheet href="Diseño/Fabrica.css">
        <title>Modificar/Eliminar</title>
    </head>
    
    <body>
        <i><%@ include file = "AreaFabrica.jsp" %></i>
        <h1 class="centro">Modificar/Eliminar Piezas</h1>
        <form method="POST" action="../Eliminar">
            <table class="default" >
    
            <tr>
            <th>Nombre</th>
            <th>Codigo</th>
            <th>Precio</th>
            <th>Seleccionar</th>
            </tr> 
        
            <%
            consultas n1= new consultas();
            n1.Pieza();
            ArrayList<pieza> piezaInventario = n1.getPiezaInventario();
            
            for (int i = 0; i < piezaInventario.size(); i++) {
                out.print("<tr>");
                out.print("<td>"+piezaInventario.get(i).getNombre()+"</td>");
                out.print("<td>"+piezaInventario.get(i).getCodigo()+"</td>");
                out.print("<td>"+"Q. "+piezaInventario.get(i).getPrecio()+"</td>");
                out.print("<td><input type='checkbox' name='eleccion' value='"+piezaInventario.get(i).getCodigo()+"'></td>");
                out.print("</tr>");
            }
            %>
            </table>
            <div class="centro">
                <input type="submit" name="Modificar" value="Eliminar" class="eliminar">
                <input type="submit" name="Modificar" value="Modificar" class="modificar"><br><br>
            </div>
            

           <%
               
            String variable=request.getParameter("mo");
            try{
                if (variable!=null && request.getParameter("mo")!=null) {
                    int posicion=-1;
                    String codigo = request.getParameter("mo");
                    for (int i = 0; i < piezaInventario.size(); i++) {
                        if (codigo.equalsIgnoreCase(piezaInventario.get(i).getCodigo())) {
                            posicion=i;
                        }
                    }
                    out.print("<h1 class='centro'>Pieza a Modificar "+piezaInventario.get(posicion).getNombre()+" codigo "+piezaInventario.get(posicion).getCodigo()+"</h1>");
                    //out.print("<div class= 'cuadroMod'>");
                    out.print("<table >");
                    out.print("<tr><th></th>");
                    out.print("<th></th></tr>");
                    out.print("<form method='POST' action='../Eliminar'>");
                    n1.TipoPieza("pieza");
                    out.print("<tr><td>Eliga Nuevo tipo para Pieza</td>");
                    out.print("<td><select name='tipo'>");
                    ArrayList<String> piezaTipo = n1.getTipoMueble();
                    for (int i = 0; i < piezaTipo.size(); i++) {
                        out.print("<option value='"+piezaTipo.get(i).toString()+"'>"+piezaTipo.get(i).toString()+"</option>");
                    }
                    out.print("</select></td></tr>");
                    out.print("<br><td>Ingrese Nuevo precio</td><td><input type='text' name='precio' value='"+piezaInventario.get(posicion).getPrecio()+"'></td></tr>");
                    out.print("<br><td>Confirma Modificacion</td><td><input type='checkbox' name='cod' value='"+codigo+"'></td></tr></table >");
                    out.print("<div class='centro'");
                    out.print("<br><input type='submit' value='Modificar Pieza' name='Modificar'class='modificar'>");
                    out.print("</div>");
                    out.print("</form>");
                }
               }catch(Exception e){}
            %>
        </form>
        
    </body>
</html>
