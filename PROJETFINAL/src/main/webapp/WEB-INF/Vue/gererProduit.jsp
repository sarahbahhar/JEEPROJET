<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
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
        </div>




        <form action="${pageContext.request.contextPath}/add-product-servlet" method="get">
            <input type="submit" name="ajouter_un_produit" value="Ajouter un produit">
        </form>
    </div>
    <hr>
</div>

</body>
</html>
