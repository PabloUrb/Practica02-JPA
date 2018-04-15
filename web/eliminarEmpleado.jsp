<%-- 
    Document   : eliminarEmpleado
    Created on : 13-abr-2018, 23:59:21
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
        <%
            List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
            if (!empleados.isEmpty()) {

        %>
        <form method="POST" action="deleteFinal">
            <table>
                <tr>
                    <% for (Empleado e : empleados) {%>
                    <th><label><input type="radio" value="<%=e.getNombreusuario()%>"><%=e.getNombrecompleto()%> - <%=e.getNombreusuario()%></label></th>
                            <%}%>
                </tr>
            </table>
            <button type="submit">Borrar</button>
        </form>
        <%
        } else {
        %> 
        <h1>esto no furula</h1>
        <% }%>
    </body>
</html>
