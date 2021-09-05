<%-- 
    Document   : DetalleFactura
    Created on : 5/09/2021, 02:40:25
    Author     : alpha
--%>

<%@page import="Objetos.Cliente"%>
<%@page import="Objetos.MuebleVendido"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK rel=StyleSheet href="../Diseño/Tienda.css">
        <title>Detalles de Factura</title>
    </head>
    <%
        HttpSession sesion2 = request.getSession(true);
        ArrayList<MuebleVendido> vendidos = sesion2.getAttribute("detalle") == null ? null :  (ArrayList)sesion2.getAttribute("detalle");
        ArrayList<Cliente> Cliente = sesion2.getAttribute("cliente") == null ? null :  (ArrayList)sesion2.getAttribute("cliente");
    %>
    <body class="CambioColor">
        <i><%@ include file = "Area2.jsp" %></i>
        <form method="POST" action="../HistorialCompra">
            <div class="centro">
                <h3 class="izquierdaform">Ingrese codigo de Factura a Consultar</h3><input type="text" name="factura" class="derechaform">
            </div><br>
            <div class="centro"><br><input type="submit" name="BuscarFactura" Value="Buscar"></div>
        </form>
        <% 
            try {
            float Total=0;
            if (vendidos.size()>0) {
   
        %>   
        <div class="hoja">
            <h2 class="centro">Factura No.<%=vendidos.get(0).getFactura()%></h2>
            <div class="Encabezado">
                <h5 class="izquierdaform">Nombre:</h5><h5 class="derechaform"><%=Cliente.get(0).getNombre()%></h5>
                <br><br><br><h5 class="izquierdaform">Nit:</h5><h5 class="derechaform"><%=Cliente.get(0).getNit()%></h5>
                <br><br><br><h5 class="izquierdaform">Direccion:</h5><h5 class="derechaform"><%=Cliente.get(0).getDireccion()%></h5>
            </div>
            <div>
                <h2 class="centro">Detalle</h2>
                <div class="detalles">
                    <div class="lado">
                        <div class="EncabezadoDetalles">Cantidad</div>
                        <%
                            for (int i = 0; i < vendidos.size(); i++) {
                                    out.print("<p>1</p>");
                                }
                        %>
                        <br><br><br><br><br>
                        <h3>-</h3>
                    </div>
                    <div class="detal">
                        <div class="EncabezadoDetalles">Detalle</div>
                        <%
                            for (int i = 0; i < vendidos.size(); i++) {
                                    out.print("<p>"+vendidos.get(i).getNombre()+"</p>");
                                }
                        %>
                        <br><br><br><br><br>
                        <h3>Total</h3>
                    </div>
                    <div class="lado">
                        <div class="EncabezadoDetalles">Precio</div>
                        <%
                            for (int i = 0; i < vendidos.size(); i++) {
                                    out.print("<p> Q.  "+vendidos.get(i).getPrecioVenta()+"</p>");
                                    Total+=vendidos.get(i).getPrecioVenta();
                                }
                        %>
                        <br><br><br><br><br>
                        <h3>Q   . <%=Total%></h3>
                    </div>
                    
                </div>
            </div>
        </div>
        <%    
                }
   
                } catch (Exception e) {
                }
                
                
        %>
    </body>
</html>
