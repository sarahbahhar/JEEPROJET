<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="./css/pageProduitvendeur.css">
    <link rel="stylesheet" href="./css/pageProduit.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="shortcut icon"  href="./img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<div>
    <hr> <!-- Repère visuel temporaire -->
    <div class="page">
        <c:choose>
            <c:when test="${empty produit}">
                <p>Aucun produit sélectionné.</p>
            </c:when>
            <c:otherwise>
                <div class="image">
                    <img src="./img/${produit.nomImage}" style="width: 100px; height: 100px; margin-right: 10px;">
                </div>
                <div class="detail">
                    <h1>${produit.titre}</h1>
                    <hr>
                    <h2>${produit.description}</h2>
                    <hr>
                    <p>Prix : ${produit.prix} €</p>
                </div>
                <div class="info">
                    <div class="prix">${produit.prix} €</div>
                    <div class="stock">${produit.stock > 0 ? 'En stock' : 'Rupture de stock'}</div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="bouton">
        <form class="bouton-golden" action="${pageContext.request.contextPath}/servlet-de-Loucas" method="post">
            <input type="number" name="produit_id" id="${produit.id}"  value="${produit.stock}" min="${produit.stock}"/>
        </form>

        <form  class="bouton-golden" method="post" action="${pageContext.request.contextPath}/delete-product-servlet">
            <input type="hidden" name="id" value="${produit.id}">
            <input type="submit" name="supprimer_un_produit" value="Supprimer ce produit">
        </form>

    </div>
    <hr> <!-- Repère visuel temporaire -->
</div>
</body>
</html>
