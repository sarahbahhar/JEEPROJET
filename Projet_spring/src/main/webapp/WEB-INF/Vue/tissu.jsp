<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/23/2023
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport">
    <title>Produits Tissu</title>
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="shortcut icon" href="./img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<h1>Tissu</h1>
<div>
    <div class="global">

        <div class="box">

            <c:forEach items="${tissuProducts}" var="produit">
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

</body>
</html>


