<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur" %>
<%@ page import="java.math.BigDecimal" %>
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
        <th>Note Moyenne</th>
        <th>Email</th>
        <th>Can Add Product</th>
        <th>Can delete Product</th>
        <th>Max Product</th>
    </tr>

    <c:forEach items="${moderators}" var="moderator">
        <tr>
            <td>${averageRatings[moderator.email]}</td>
            <td>${moderator.email}</td>
            <td>${moderator.peutAjouterProduit}</td>
            <td>${moderator.peutSupprimerProduit}</td>
            <td>${moderator.maxProduitsLigne}</td>
            <td>
                <form action="${pageContext.request.contextPath}/delete-moderator-servlet" method="post">
                    <input type="hidden" name="email" value="${moderator.email}" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
    <input type="hidden" name="path" value="editModerator.jsp" />
    <button class="lien" type="submit">Edit Moderators</button>
</form>
</body>
</html>
