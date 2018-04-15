<%-- 
    Document   : modificarEmpleado
    Created on : 14-abr-2018, 0:05:00
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
        <form action="modificarEmpleado">
            <p>Nombre Completo: <input  type="text" name="nombre" value="<%=e.getNombrecompleto()%>"/></p>
            <p>Ciudad: <input  type="text" name="ciudad" value="<%=e.getCiudad()%>"/></p>
            <p>Telefono: <input  type="tel" name="telefono" value="<%=e.getTelefono()%>"/></p>
            <p><input type="submit" value="Modificar Empleado"/></p>
        </form>
        <%
            }
        %>
    </body>
</html>
