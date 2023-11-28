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
    <link rel="stylesheet" href="./css/vendeur.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
</head>
<body>





<div>
    <div class="global">
        <div class="box">
            <c:choose>
                <c:when test="${not empty produitsDansPanier}">
                    <header>
                        <h1>Panier</h1>
                    </header>
                <c:forEach items="${panier}" var="produitPanier">


                    <div>
                    <form class="article" action="${pageContext.request.contextPath}/redirect-product-order-servlet" method="post">

                            <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;">


                                <c:forEach var="produit" items="${produitsDansPanier}">
                                    <c:if test="${produit.id eq produitPanier.produitId}">
                                        <p>Titre : ${produit.titre}</p>
                                        <p>Prix : ${produit.prix} €</p>
                                    </c:if>
                                </c:forEach>

                                <p>Quantite : ${produitPanier.quantite}</p>

                            </button>






                        <input type="hidden" name="produit_id" value="${produitPanier.produitId}" />


                    </form>

                        <form action="${pageContext.request.contextPath}/modify-quantity-servlet" method="post">
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
                        </form>


                    </c:forEach>
                    </div>

                <div>
                    <h2>Total</h2>
                    <p id="total-amount">${total} €</p>
                </div>


                    <form class='style' action="${pageContext.request.contextPath}/VerifyStockServlet" method="post">
                        <input type="hidden" name="path" value="payment.jsp" />
                        <button class="bouton-golden" type="submit">valider</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <header>
                        <h1>Votre panier est vide</h1>
                    </header>

                </c:otherwise>
            </c:choose>
        </div>
    </div>


</div>




</body>
</html>
