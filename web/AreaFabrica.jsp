<%-- 
    Document   : AreaFabrica
    Created on : 15/08/2021, 21:53:22
    Author     : alpha
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="Objetos.pieza"%>
<%@page import="procesos.consultas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form>
        <%
            consultas n1= new consultas();
            n1.Pieza();
            ArrayList<pieza> piezaInventario = n1.getPiezaInventario();
            for (int i = 0; i < piezaInventario.size(); i++) {
                    out.print("<input type='checkbox' name='pieza' value='"+piezaInventario.get(i).getCodigo()+"'>");
                    out.print(piezaInventario.get(i).getNombre()+"<br>");
                }
        %>
        <br><br><br>
        <input type="submit" action="AreaFabrica.jsp" value="Imprimir">
        <%
            if (request.getParameterValues("pieza")==null) {
                    
            }else{
                String[] mostrar=request.getParameterValues("pieza");
                for (int i = 0; i < request.getParameterValues("pieza").length; i++) {
                        out.print("<h1>"+mostrar[i]+"</h1>");
                    }
            }
        %>
        </form>
                
    </body>
</html>
