<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>


    <meta charset="utf-8">
    <meta name="viewport">
    <title>Produits Lin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="./img/logo_onglet.ico" type="image/x-icon">
    <title>Lien Expiré</title>
    <%@ include file="header.jsp" %>


</head>
<body>
<div class="container">
    <h1>Lien de Réinitialisation Expiré</h1>
    <p>Le lien de réinitialisation de votre mot de passe est expiré ou invalide. Veuillez demander un nouveau lien.</p>

    <form class='style' action="${pageContext.request.contextPath}/login" method="get">
        <button class='lien' type='submit'>Retour à la page de connexion</button>
    </form>

</div>
</body>

<footer>

</footer>
</html>
