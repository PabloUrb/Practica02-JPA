<%-- 
    Document   : menuUsuario
    Created on : 10-abr-2018, 9:32:55
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
        <h1>Hello <%=e.getNombreusuario()%></h1>
        <form action="altaEmpleado.html">
            <input type="submit" value="Modificar perfil"/>
        </form>
        <form action="altaEmpleado.html">
            <input type="submit" value="Canviar contraseña"/>
        </form>
        <form action="altaEmpleado.html">
            <input type="submit" value="Crear una incidencias"/>
        </form>
        <form action="altaEmpleado.html">
            <input type="submit" value="Incidencias recividas"/>
        </form>
        <form action="altaEmpleado.html">
            <input type="submit" value="Incidencias enviadas"/>
        </form>
        <form action="altaEmpleado.html">
            <input type="submit" value="Mi posicion en el rancking"/>
        </form>
        <%} else {
        %><h1>esto no furula</h1>
        <%
            }%>
    </body>
</html>
