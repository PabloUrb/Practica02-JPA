<%-- 
    Document   : crearIncidencias
    Created on : 12-abr-2018, 10:34:10
    Author     : pablourbano
--%>

<%@page import="java.util.List"%>
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
                List<Empleado> empleados = (List<Empleado>) request.getAttribute("Empleados");
        %>
        <h1>Hello <%=e.getNombreusuario()%></h1>
        <form method="POST" action="crearIncidencias"> 
            <%if (empleados.size() != 0) {%>
            Destinatario:
            <select name="destino">

                <%for (Empleado em : empleados) {%>

                <option value="<%= em.getNombreusuario()%>"><%= em.getNombreusuario()%></option> 

                <%}%>
            </select>
            <%} else {%>
            no hay empleados
            <% } %>
            <p>Tipo:
                <select name="tipo">
                    <option value="urgente">Urgente</option>
                    <option value="normal">Normal</option>
                </select>
            </p>
            <p>Detalle: <input type="text" name="detalle" required></p>

            <p><input type="submit" value="Crear"></p>
        </form>
        <%} else {
        %><h1>esto no furula</h1>
        <%
            }%>
    </body>
</html>
