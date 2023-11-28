<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Cartes</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/myCard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/creditcardlist.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <%@ include file="header.jsp" %>
</head>
<body>
    <c:if test="${empty accounts}">
        <h1>Vous n'avez pas de compte bancaire enregistré.</h1>
    </c:if>

    <c:if test="${not empty accounts}">
    <div style='margin: auto; width: 400px; max-width: 95vw; font-family: "Helvetica Neue", Helvetica, sans-serif; box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23); padding: 10px; margin-top: 50px; margin-bottom: 50px; border-radius: 15px; background: #f9fcff;'>
        <h1 style="font-weight: 500; text-align: center; ">Cartes enregistrée</h1>
        <c:forEach items="${accounts}" var="account">

        <div class="credit-card visa selectable">
            <div class="credit-card-last4">
                    ${fn:substring(account.numero, fn:length(account.numero) - 4, fn:length(account.numero))}
            </div>

            <div class="credit-card-expiry">
                    ${account.date}
            </div>
            <div class="credit-card-name"> <!-- Added div for cardholder's name -->
                    ${account.nom} <!-- Placing the cardholder's name here -->
            </div>
            <form action="${pageContext.request.contextPath}/delete-bank-account-servlet" method="post">
                <input type="hidden" name="email" value="${account.email}" />
                <input type="hidden" name="id" value="${account.id}" />
                <button type="submit" class="delete-button">
                    <i class="fas fa-trash-alt"></i>
                </button>

            </form> </div>

        </c:forEach>

    </c:if>
    <div style="text-align: center">
        <form action="${pageContext.request.contextPath}/redirect-servlet" method="post">
            <input type="hidden" name="path" value="addPaymentMode.jsp" />

            <button class="lien" type="submit">Ajouter Carte</button>
        </form>
    </div>
</div>
</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>
