<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Produit" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panier</title>
    <link rel="stylesheet" href="../../css/panier.css">
</head>
<body>
<header>
    <h1>Panier</h1>
</header>

<div class="box">
    <c:forEach items="${panier}" var="produitPanier">
        <div class="produit">

            <div>
                <h5>${ProduitPanier}</h5>
                <p>Id : ${produitPanier.produitId}</p>
                <p>Quantite : ${produitPanier.quantite}</p>






            </div>
        </div>
    </c:forEach>

    <div>
        <h2>Total</h2>
        <p id="total-amount">${panier.total} €</p>
    </div>



</div>
</body>
</html>
