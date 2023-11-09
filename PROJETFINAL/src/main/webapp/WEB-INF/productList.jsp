<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Produit" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Produit List</title>
</head>
<body>
<h1>Produit List</h1>
<table>
    <tr>
        <th>id</th>
        <th>titre</th>
        <th>nomImage</th>
        <th>stock</th>
        <th>email</th>


    </tr>

    <c:forEach items="${produits}" var="produit">
    <tr>
        <td>${produit.id}</td>
        <td>  ${produit.titre}  </td>
        <td>  ${produit.nomImage}  </td>
        <td>  ${produit.stock}  </td>
        <td>  ${produit.email}  </td>

        </c:forEach>
</table>
<br>
<br>

</body>
</html>
