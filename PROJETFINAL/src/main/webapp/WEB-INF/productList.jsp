<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.LignePanier" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panier</title>
</head>
<body>
<h1>Panier</h1>
<table>
    <!-- En-tête du tableau -->
    <thead>
    <tr>
        <th>id</th>
        <th>titre</th>
        <th>nomImage</th>
        <th>stock</th>
        <th>email</th>
        <th>Quantité</th>
    </tr>
    </thead>
    <tbody>
    <!-- Afficher les produits dans le panier -->
    <c:forEach items="${panier}" var="lignePanier">
        <tr>
            <td>${lignePanier.produit.id}</td>
            <td>${lignePanier.produit.titre}</td>
            <td>${lignePanier.produit.nomImage}</td>
            <td>${lignePanier.produit.stock}</td>
            <td>${lignePanier.produit.email}</td>
            <td>${lignePanier.stock}</td>
        </tr>
    </c:forEach>
    </tbody>
    <!-- Pied de tableau avec le total -->
    <tfoot>
    <tr>
        <td colspan="5">Total:</td>
        <td>${TTC}</td>
    </tr>
    </tfoot>
</table>
</body>
</html>

