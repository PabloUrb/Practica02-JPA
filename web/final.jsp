<%-- 
    Document   : final
    Created on : 28-mar-2018, 18:25:55
    Author     : pablourbano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>incidencias</title>
    </head>

    <%

        String status = (String) request.getAttribute("status");

        if (status != null) {

    %>
    <h1><%=status%></h1>
    <%
        }
    %>


</html>
