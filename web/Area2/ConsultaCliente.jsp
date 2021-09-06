<%-- 
    Document   : ConsultaCliente
    Created on : 4/09/2021, 15:36:44
    Author     : alpha
--%>

<%@page import="Objetos.Cliente"%>
<%@page import="procesos.FormatoFecha"%>
<%@page import="Objetos.MuebleVendido"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Cliente</title>
    </head>
    <%
        HttpSession sesion2 = request.getSession(true);
        ArrayList<MuebleVendido> vendidos = sesion2.getAttribute("history") == null ? null :  (ArrayList)sesion2.getAttribute("history");
        ArrayList<Cliente> usuario2 = sesion2.getAttribute("clientebusca") == null ? null :  (ArrayList)sesion2.getAttribute("clientebusca");
    %>
    <body>
        <i><%@ include file = "Area2.jsp" %></i>
        <div class="centro">
            <form action="../HistorialCompra" method="POST">
                <h3>Ingrese Nit de Usuario</h3><input type="text" name="usuario">
                <input type="submit" name="buscar" value="Buscar">
                <br>Ingrese Fecha Inicio<input type="Date" name="inicial">
                Ingrese Fecha Inicio<input type="Date" name="final">
            
            </form>
        </div>
        <div>
            <%
                if (request.getParameter("er")!=null) {
                        out.print("<h3>El usuario ingresado o las fechas ingresadas son incorrectas</h3>");
                    }
                if (request.getParameter("not")!=null) {
                    out.print("<h3>El Usuario ingresado no realizo compras entre las fechas establecidas</h3>");
                }
            %>
        </div>
        <div class="centro">
            <%
                try {
                    if (request.getParameter("a").equals("a")){
                    
            %>
            <h3>Nit: <%=usuario2.get(0).getNit()%></h3>
            <h3>Nombre: <%=usuario2.get(0).getNombre()%></h3>
            <%
                    }
                } catch (Exception e) {
                    }
            %>
        </div>
        <div>
            <table>
                <tr>
                    <th> Fecha</th>
                    <th> Factura No.</th>
                    <th> Mueble</th>
                    <th> Precio </th>
                    <th> Vendedor</th>
                </tr>
                <%
                    try {
                    
                        if (request.getParameter("a").equals("a")) {
                            String mueble="";
                            for (int i = 0; i < vendidos.size(); i++) {
                                String fecha=FormatoFecha.CambiarFormatoFecha(vendidos.get(i).getEnsamble().toString());
                            %>
                            <tr>
                                <td><%=fecha%></td>
                                <td><%=vendidos.get(i).getFactura()%></td>
                                <td><%=vendidos.get(i).getNombre()%></td>
                                <td><%=vendidos.get(i).getPrecioVenta()%></td>
                                <td><%=vendidos.get(i).getVendedor()%></td>
                            </tr>
                
                
                <%
                
                                }
                            }
                        } catch (Exception e) {
                        }
                %>
            </table>
            
        </div>
    </body>
</html>
