<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Commandes</title>
    <link rel="stylesheet" href="./css/vendeur.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
</head>
<body>

<div>
    <div class="global">
        <div class="box">
            <c:choose>
                <c:when test="${empty commandes}">
                    <div class="no-commandes">
                        <h1>Vous n'avez pas de commande en cours.</h1>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${commandes}" var="commande">
                        <form action="${pageContext.request.contextPath}/commande-detail-servlet" method="post">
                            <div class="article">
                                <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;">
                                    <c:choose>
                                        <c:when test="${type eq 'pageMySales'}">
                                            <div>
                                                <h5>Vente ${loop.index + 1}</h5>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div>
                                                <h5>Commande ${commande.numero}</h5>
                                                <p>Date : ${commande.dateDePaiement}</p>
                                                <p>Prix : ${commande.total} €</p>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </button>
                                <input type="hidden" name="commande_id" value="${commande.numero}">
                            </div>
                        </form>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

    </div>
</div>






</div>
<hr>


</body>
</html>
