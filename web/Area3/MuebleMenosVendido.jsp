<%-- 
    Document   : MuebleMasVendido
    Created on : 6/09/2021, 05:22:39
    Author     : alpha
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="procesos.FormatoFecha"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mueble con mayor venta</title>
        <script>
            function Descargar_csv(fecha_In , fecha_fin, table_id, separator = ',') {
            // Select rows from table_id
            var rows = document.querySelectorAll('table#' + table_id + ' tr');
            // Construct csv
            var csv = [];
            for (var i = 0; i < rows.length; i++) {
            var row = [], cols = rows[i].querySelectorAll('td, th');
            for (var j = 0; j < cols.length; j++) {
            var data = cols[j].innerText.replace(/(\r\n|\n|\r)/gm, '').replace(/(\s\s)/gm, ' ')
            data = data.replace(/"/g, '""');
            row.push('"' + data + '"');
            }
            csv.push(row.join(separator));
            }
            var csv_string = csv.join('\n');
            // Download it
            var filename = 'Reporte_Mueble_Menos_Vendido_' + fecha_In + '_al_' + fecha_fin+ '.csv';
            var link = document.createElement('a');
            link.style.display = 'none';
            link.setAttribute('target', '_blank');
            link.setAttribute('href', 'data:text/csv;charset=utf-8,' + encodeURIComponent(csv_string));
            link.setAttribute('download', filename);
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
            }
            
        </script>
    </head>
    <body>
        <i><%@ include file = "Area3.jsp" %></i>
        <h1>Mueble con Menor Reporte de Ventas en Intervalo de Tiempo</h1>
        <form action="../ReporteMuebles" method="POST">
            <div class="centro">
                <h3>Ingrese Fecha Inicial <input type="date" name="inicio"> &nbsp;&nbsp;&nbsp;&nbsp;Ingrese Fecha Final <input type="date" name="final"></h3>
                <input type="submit" name="muebletiempo" value="Generar Reporte">
            </div>
        </form>
        <%
                try {
                        if (request.getParameter("a").equals("b")) {
                                out.print("<h2 class='centro'>Las Fechas Ingresadas son Incorrectas</h2>");
                            }
                    } catch (Exception e) {
                    }
            %>
        <div>
            <table id="alph">
            <%
                try {
                    float total=0;
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    if (request.getParameter("a").equals("a")) {
            %>
            <tr>
                <td COLSPAN=2></td>
                <th COLSPAN=2>Reporte de <%=FormatoFecha.CambiarFormatoFecha(fechas.get(0))%> al <%=FormatoFecha.CambiarFormatoFecha(fechas.get(1))%></th>
                <td COLSPAN=2></td>
            </tr>
                <tr>
                    <th>Fecha</th>
                    <th>No. Factura</th>
                    <th>Mueble</th>
                    <th>Costo Fabricacion</th>
                    <th>Precio venta</th>
                    <th>Ganacia Individual</th>
                </tr>
                
                
            <%                     
                    for (int i = 0; i < vendidos.size(); i++) {
                    total+=vendidos.get(i).getPrecioVenta()-vendidos.get(i).getPrecioEnsamble();
            %>
            
                
                        <tr>
                            <td><%=FormatoFecha.CambiarFormatoFecha(formato.format(vendidos.get(i).getEnsamble()))%></td>
                            <td><%=vendidos.get(i).getFactura()%></td>
                            <td><%=vendidos.get(i).getNombre()%></td>
                            <td>Q. <%=vendidos.get(i).getPrecioEnsamble()%></td>
                            <td>Q. <%=vendidos.get(i).getPrecioVenta()%></td>
                            <td>Q. <%=vendidos.get(i).getPrecioVenta()-vendidos.get(i).getPrecioEnsamble()%></td>
                        </tr>
                
            
            <%
                        if ((i+1)==vendidos.size()) {
            %>
                        <tr>
                            <th COLSPAN=5>Total en Intervalo de Tiempo</th>
                            <td><h3>Q. <%=total%></h3></td>
                        </tr>
            <%
                            }
                    }
                    }
                } catch (Exception e) {
                }
            %>
            </table>
        </div>
            <%
                try {


                if (request.getParameter("a").equals("a")) {

                %>

            <div class="centro"><button href="#" onclick="Descargar_csv('<%=FormatoFecha.CambiarFormatoFecha(fechas.get(0))%>', '<%=FormatoFecha.CambiarFormatoFecha(fechas.get(1))%>', 'alph' );">Exportar a CSV</button></div><br><br><br><br>
            <%
                }
                } catch (Exception e) {}
            %>
    </body>
</html>
