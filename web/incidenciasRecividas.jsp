<%-- 
    Document   : incidenciasRecividas
    Created on : 11-abr-2018, 23:13:38
    Author     : pablourbano
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Incidencia"%>
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
                List<Incidencia> incidencias = (List<Incidencia>) request.getAttribute("Incidencias");
        %>
        <h1>Hello <%=e.getNombreusuario()%></h1>
        <%if (incidencias.size() != 0) {

        %>
        <h2>Estas son las incidencias que has enviado</h2>
        <table>
            <tr>
                <th>idincidencia</th>
                <th>origen</th>
                <th>destino</th>
                <th>fechahora</th>
                <th>detalle</th>
                <th>tipo</th>
            </tr>
            <%for (Incidencia i : incidencias) {%>
            <tr>
                <th><%= i.getIdincidencia()%></th>
                <th><%= i.getOrigen().getNombreusuario()%></th>
                <th><%= i.getDestino().getNombreusuario()%></th>
                <th><%= i.getFechahora()%></th>
                <th><%= i.getDetalle()%></th>
                <th><%= i.getTipo()%></th>
            </tr>
            <%}%>
        </table>
        <%} else {%>
        <h1>no tienes incidencias enviadas</h1>
        <%}
        } else {
        %>
        <h1>esto no furula</h1>
        <%
            }%>
    </body>
</html>
