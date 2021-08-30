<%-- 
    Document   : prueba
    Created on : 28/08/2021, 23:29:47
    Author     : alpha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        <%
            if (request.getParameter("eleccion")!=null) {
                    out.print("capto que hay parametro y el parametro es "+request.getParameter("eleccion"));
                }
            
        %>
    </body>
</html>
