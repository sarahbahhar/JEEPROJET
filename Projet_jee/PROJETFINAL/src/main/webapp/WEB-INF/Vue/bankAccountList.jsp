<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Cartes</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/myCard.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<div style="width: 40%; margin: auto;">


    <c:if test="${empty accounts}">
        <h1>Vous n'avez pas de compte bancaire enregistré.</h1>
    </c:if>

    <c:if test="${not empty accounts}">
        <h1>Liste des cartes enregistre : </h1>
        <c:forEach items="${accounts}" var="account">
            <table class="account-table">
                <tr>
                    <td>Nom:</td>
                    <td>${account.nom}</td>
                </tr>
                <tr>
                    <td>Numero:</td>
                    <td>**** **** **** ${fn:substring(account.numero, fn:length(account.numero) - 4, fn:length(account.numero))}
                    </td>
                </tr>
                <tr>
                    <td>Date d'expiration:</td>
                    <td>${account.date}</td>
                </tr>
                <tr>
                    <td>CVV:</td>
                    <td>${account.cvv}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form action="${pageContext.request.contextPath}/delete-bank-account-servlet" method="post">
                            <input type="hidden" name="email" value="${account.email}" />
                            <input type="hidden" name="id" value="${account.id}" />
                            <button type="submit" class="bouton-golden">Delete</button>
                        </form>
                    </td>
                </tr>
            </table>
        </c:forEach>

    </c:if>
    <div style="text-align: right">
        <form action="${pageContext.request.contextPath}/redirect-servlet" method="post">
            <input type="hidden" name="path" value="addPaymentMode.jsp" />

            <button class="bouton-golden" type="submit">Ajouter Carte</button>
        </form>
    </div>


</div>
</body>
</html>
