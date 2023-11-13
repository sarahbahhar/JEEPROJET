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
  <c:forEach items="${produitcommandes}" var="produitCommande">
    <div class="produit">


      <p>Titre : ${produitCommande.titre}</p>
      <p>Prix : ${produitCommande.prix} €</p>

      <div>
        <p>Quantite : ${produitCommande.quantite}</p>
      </div>
    </div>
  </c:forEach>





</div>
</body>
</html>
