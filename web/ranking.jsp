<%-- 
    Document   : ranking
    Created on : 14-abr-2018, 0:29:45
    Author     : pablourbano
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Empleado"%>
<%@page import="entities.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ranking</h1>
        <%
            List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");

            if (empleados.isEmpty()) {
        %>
        <h2>No hay ranking</h2>
        <%
        } else {
        %>
        <table>
            <tr>
                <%
                    for (Empleado e : empleados) {
                %>
                <th><%= e.getNombrecompleto()%>  - <%= e.getNombreusuario()%></th>
                    <%
                        }
                    %>
            </tr>
        </table>
        <%
            }
        %>
    </body>
</html>
