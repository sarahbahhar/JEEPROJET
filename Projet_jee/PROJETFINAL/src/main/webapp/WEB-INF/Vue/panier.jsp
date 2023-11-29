<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panier</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/commande.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
</head>
<body>
<c:choose>
    <c:when test="${empty produitsDansPanier}">
        <div class="no-commandes">
            <h1>Votre panier est vide.</h1>
        </div>
    </c:when>

    <c:otherwise>
        <div class="command">
            <section>
                <h1 id="total-amount">Panier :  ${total} €</h1>
                <c:forEach items="${panier}" var="produitPanier">
                    <details>
                        <summary>
                            <c:forEach var="produit" items="${produitsDansPanier}">
                            <c:if test="${produit.id eq produitPanier.produitId}"><div>
                            <span style="background-color: #f2dcbb;">
                                <img style="width: 63px;height: 33px;" src="./img/${produit.nomImage2}">
                            </span>
                                <h3>
                                        <strong>${produit.titre}</strong><p></p>
                                </h3>
                                <span> ${produit.prix} € x
                                        </c:if>
                                </c:forEach>
                                ${produitPanier.quantite} </span>
                            </div>
                        </summary>
                                        <dl>
                                            <div>
                                                <dt>Quantité</dt>
                                                <dd><form action="${pageContext.request.contextPath}/modify-quantity-servlet" method="post">
                                                    <input type="hidden" name="produit_id" value="${produitPanier.produitId}">
                                                    <input type="hidden" name="email" value="${sessionScope.email}" />
                                                    <c:forEach var="produit" items="${produitsDansPanier}">
                                                        <c:if test="${produit.id eq produitPanier.produitId}">
                                                            <select name="quantite" onchange="this.form.submit()">
                                                                <c:forEach var="i" begin="0" end="${produit.stock}">
                                                                    <option value="${i}" <c:if test="${i eq produitPanier.quantite}">selected</c:if>>${i}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </c:if>
                                                    </c:forEach>
                                                    <noscript><input type="submit" value="Submit"></noscript>
                                                </form></dd>
                                            </div>
                                            <div>
                                                <dd> <form class="article" action="${pageContext.request.contextPath}/redirect-product-order-servlet" method="post">
                                                    <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;">
                                                        <i class="fas fa-search" style="font-size: 1.5em;"></i>
                                                    </button>
                                                    <input type="hidden" name="produit_id" value="${produitPanier.produitId}" />
                                                </form></dd>
                                            </div>
                                        </dl>
                    </details>
                </c:forEach>
                <form class='style' style="margin-left:300px" action="${pageContext.request.contextPath}/VerifyStockServlet" method="post">
                    <input type="hidden" name="path" value="payment.jsp" />
                    <button class="lien" type="submit">Valider</button>
                </form></section></div>
    </c:otherwise>
</c:choose>
</body>
</html>
