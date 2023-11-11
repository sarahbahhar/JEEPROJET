<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur" %>
<%@ page import="java.util.List" %>
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

    <c:forEach items="${moderators}" var="moderator">
        <tr>
            <td>${moderator.email}</td>
            <td>  ${moderator.peutAjouterProduit}  </td>
            <td>  ${moderator.peutSupprimerProduit}  </td>
            <td>  ${moderator.maxProduitsLigne}  </td>
            <td><button>EDIT</button></td>
            <td><form action="${pageContext.request.contextPath}/delete-moderator-servlet" method="post">
                <input type="hidden" name="email" value="${moderator.email}" />
                <button type="submit">Delete</button>
            </form></td>
    </c:forEach>
</table>
<br>
<br>
<button>ADD NEW MODERATOR</button>
</body>
</html>
