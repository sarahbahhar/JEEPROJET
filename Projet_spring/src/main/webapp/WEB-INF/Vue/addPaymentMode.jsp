<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter Carte</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/myCard.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>

<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="${pageContext.request.contextPath}/new-bank-account-servlet" method="post">
            <h1>Ajouter un Mode de Paiement</h1>
            <input type="text" id="nom" name="nom" placeholder="Nom sur la Carte" required>
            <input type="text" id="numero" name="numero" pattern="[0-9]{16}" placeholder="Numéro de Carte" required>
            <input type="text" id="expiration" name="expiration" pattern="(0[1-9]|1[0-2])\/[0-9]{2}" placeholder="Date d'Expiration (MM/AA)" required>
            <input type="text" id="cvv" name="cvv" pattern="[0-9]{3}" placeholder="CVV" required>
            <input type="hidden" name="email" value="${sessionScope.email}" />
            <button class="lien" type="submit" >Ajouter</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/nouvelleCarte.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>
</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>
