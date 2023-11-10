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
        <link rel="stylesheet" href="./css/produit.css">
        <link rel="stylesheet" href="./css/style.css">
        <link rel="shortcut icon"  href="./img/logo_onglet.ico" type="image/x-icon">
    </head>

</head>
<body>

<jsp:include page="/Vue/header.jsp" />



<div class="box">
    <c:forEach items="${produits}" var="produit">
        <form>
            <div class="produit">
                <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;" >

                    <img src="./img/${produit.nomImage}" alt="${produit.titre}" style="width: 100px; height: 100px; margin-right: 10px;">
                    <div>
                        <h5>${produit.titre}</h5>
                        <p>Voici une mini description</p>
                        <p>Prix : Le PRIX €</p>
                    </div>
                </button>
                <input type="hidden" name="produit_id" value="${produit.id}">
            </div>

        </form>
    </c:forEach>


</div>



<br>
<br>

</body>
</html>