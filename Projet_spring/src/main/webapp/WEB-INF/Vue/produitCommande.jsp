<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Produit</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/vendeur.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
  <%@ include file="header.jsp" %>
</head>
<body>

<div>
  <div class="global">
    <div class="box">
  <c:forEach items="${produitcommandes}" var="produitCommande">


    <form class="style" action="${pageContext.request.contextPath}/redirect-product-order-servlet" method="post">
      <div class="article">
        <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;">


          <div>
              <h1>Titre : ${produitCommande.titre}</h1>
              <p>Prix : ${produitCommande.prix} €</p>
              <p>Quantite : ${produitCommande.quantite}</p>
          </div>
        </button>


      </div>
      <input type="hidden" name="produit_id" value="${produitCommande.idProduit}" />
      <input type="hidden" name="commande_id" value="${produitCommande.commandeNumero}" />

    </form>
  </c:forEach>

    </div>
  </div>


</div>
</body>
<footer>
  <%@ include file="footer.jsp" %>
</footer>
</html>
