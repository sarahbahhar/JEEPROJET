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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productList.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
</head>
<body>


<div class="container">
    <table>
        <c:forEach items="${produits}" var="produit" varStatus="loop">
        <c:if test="${loop.index % 4 == 0}">
        </tr><tr>
        </c:if>
        <td>
            <form action="${pageContext.request.contextPath}/product-details" method="post">
                <button style="border:none; background-color:#f7f6f2;"type="submit"><div class="card">
                    <img src="./img/${produit.nomImage}" alt="${produit.titre}">
                    <div class="image-box">
                        <img src="./img/${produit.nomImage2}" alt="${produit.titre}">
                    </div>
                    <div class="content-box">
                        <h2>${produit.titre}</h2>
                        <div class="color">
                            <p>${produit.miniDescription}</p>
                        </div>
                        <div class="size">
                            <p>${produit.prix}$</p>
                        </div>
                    </div>
                </div></button>
                <input type="hidden" name="produit_id" value="${produit.id}">
                <input type="hidden" name="type" value="pageProduitVendeur">

            </form>
        </td>
        </c:forEach>
    </table>
</div>
<div>
    <c:if test="${fn:length(produits) == 0}">
                <h1 style="margin-left: 433px;">Vous n'avez pas de produit en ligne</h1>
            </c:if>
        </div>

        <c:if test="${sessionScope.canAddProduct}">
            <c:choose>
                <c:when test="${fn:length(produits) lt sessionScope.maxProductsPerLine}">
                    <form style="margin-left: 601px;width: 400px;" action="${pageContext.request.contextPath}/add-product-servlet" method="get">
                        <button  class="lien" type="submit" name="ajouter_un_produit">Ajouter un produit</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="red-text">Vous avez atteint votre limite de produits en ligne.</div>
                </c:otherwise>
            </c:choose>

        </c:if>


</body>
</html>
