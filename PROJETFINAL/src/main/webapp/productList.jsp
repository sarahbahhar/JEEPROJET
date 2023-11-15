<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Produit" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="utf-8">
        <meta name="viewport">
        <title>Accueil</title>
        <link rel="stylesheet" href="./css/home.css">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="shortcut icon"  href="./img/logo_onglet.ico" type="image/x-icon">
    </head>

</head>
<body>




<div>
    <div class="global">

<div class="box">


    <c:forEach items="${produits}" var="produit">
        <form action="${pageContext.request.contextPath}/product-details" method="post">
            <br>
            <div class="article">
                <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;">

                    <img src="./img/${produit.nomImage}" alt="${produit.titre}" style="width: 100px; height: 100px; margin-right: 10px;">
                    <div>
                        <h5>${produit.titre}</h5>
                        <p>${produit.miniDescription}</p>
                        <p>Prix : ${produit.prix} €</p>
                    </div>
                </button>
                <input type="hidden" name="produit_id" value="${produit.id}">
                <input type="hidden" name="type" value="pageProduit">
            </div>


        </form>
    </c:forEach>


</div>
    </div>

</div>



<br>
<br>

</body>
</html>