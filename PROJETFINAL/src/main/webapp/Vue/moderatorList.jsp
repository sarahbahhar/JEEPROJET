<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur"
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moderator List</title>
</head>
<body>
<h1>Moderator List</h1>
<table>
    <tr>
        <th>Email</th>
        <th>Can Add Product</th>
        <th>Can delete Product</th>
        <th>Max Product</th>
    </tr>
    <td>Transport</td>
    <%
        List<Moderateur> personne = (List<Moderateur>) request.getAttribute("moderators");
        if (personne != null && !personne.isEmpty()) {
            String personneTransports = personne.get(0).getEmail();
    %>
    <tr><td></td><td><%=personneTransports%></td></tr>
    <%
    }else{%>
    <tr><td></td><td>dddd</td></tr>
    <%
        }
    %>
    </tr>
    <c:forEach items="${moderators}" var="moderator">
        <tr>
            <td>${moderator.email}</td>
            <td>${moderator.addProd}</td>
            <td>${moderator.removeProd}</td>
            <td>${moderator.maxProd}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
