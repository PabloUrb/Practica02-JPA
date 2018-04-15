<%-- 
    Document   : cambiarContraseña
    Created on : 13-abr-2018, 15:04:28
    Author     : pablourbano
--%>

<%@page import="entities.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Empleado e = (Empleado) session.getAttribute("empleado");
            if (e != null) {
        %>
        <h1>Modificar contraseña</h1>
        <form method="POST" action="cambiarContra">
            <p>Contraseña actual<input id="name" type="password" name="actual"/></p>
            <p>Contraseña nueva<input type="password" name="contra1" required/></p>
            <p>Confirmar Contraseña<input type="password" name="contra2" required/></p>
            <p><input type="submit" value="Modificar"/></p>
        </form>
        <%} else {%>
        <h2>no furula</h2>
        <%}%>
    </body>
</html>
