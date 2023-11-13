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
        <div class="box">
            <c:forEach items="${commandes}" var="commande">
                <form action="${pageContext.request.contextPath}/commande-detail-servlet" method="post">
                    <div class="article">
                        <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;">
                            <div>
                                <h5>Commande ${commande.numero}</h5>
                                <p>Date : ${commande.dateDePaiement}</p>
                                <p>Prix : ${commande.total} €</p>
                            </div>
                        </button>
                        <input type="hidden" name="commande_id" value="${commande.numero}">


                    </div>
                </form>
            </c:forEach>
        </div>





    </div>
    <hr>
</div>

</body>
</html>
