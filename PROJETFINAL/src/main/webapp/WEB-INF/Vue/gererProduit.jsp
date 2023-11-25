<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Produits</title>
    <link rel="stylesheet" href="./css/vendeur.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
</head>
<body>

<div>
    <div class="global">
        <div class="box" style="display: grid;">

            <c:forEach items="${produits}" var="produit">
                <form action="${pageContext.request.contextPath}/product-details" method="post">
                    <div class="article" style="display: flex; justify-content: center; align-items: center;">
                        <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;" >
                            <img src="./img/${produit.nomImage}" style="width: 100px; height: 100px; margin-right: 10px;">
                            <div>
                                <h5>${produit.titre}</h5>
                                <p>${produit.miniDescription}</p>
                                <p>Prix : ${produit.prix} €</p>
                            </div>
                        </button>
                        <input type="hidden" name="produit_id" value="${produit.id}">
                        <input type="hidden" name="type" value="pageProduitVendeur">


                    </div>
                </form>
            </c:forEach>
            <c:if test="${fn:length(produits) == 0}">
                <h1>Vous n'avez pas de produit en ligne</h1>
            </c:if>
        </div>



        <c:if test="${sessionScope.canAddProduct}">
            <c:choose>
                <c:when test="${fn:length(produits) lt sessionScope.maxProductsPerLine}">
                    <form action="${pageContext.request.contextPath}/add-product-servlet" method="get">
                        <input type="submit" name="ajouter_un_produit" value="Ajouter un produit">
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="red-text">Vous avez atteint votre limite de produits en ligne.</div>
                </c:otherwise>
            </c:choose>

        </c:if>

    </div>
    <hr>
</div>

</body>
</html>
