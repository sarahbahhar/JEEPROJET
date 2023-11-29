<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier Mon Profil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <link rel="shortcut icon" href="./img/logo_onglet.ico" type="image/x-icon">
</head>
<body>

<div class="container special-container" id="container">
    <div class="form-container sign-in-container">
        <form method="POST" action="${pageContext.request.contextPath}/ModifyInfoServlet">
            <h1>Modifier Mon Profil</h1>
            <input type="text" id="prenom" name="prenom" value="${sessionScope.InfoCompte.prenom}" placeholder="Prénom" required>
            <input type="text" id="nom" name="nom" value="${sessionScope.InfoCompte.nom}" placeholder="Nom" required>
            <input type="date" id="date" name="date" value="${sessionScope.InfoCompte.dateNaissance}" placeholder="JJ/MM/AAAA" required>
            <input type="text" id="telephone" name="telephone" value="${sessionScope.InfoCompte.telephone}" placeholder="N° telephone" required>
            <input type="text" id="adresse" name="adresse" value="${sessionScope.InfoCompte.adresse}"placeholder="Adresse" required>
            <input type="text" id="ville" name="ville" value="${sessionScope.InfoCompte.ville}" placeholder="Ville" required>
            <input type="number" id="codePostal" name="codePostal" value="${sessionScope.InfoCompte.codePostal}" placeholder="Code Postal" required>
            <input type="text" id="pays" name="pays" value="${sessionScope.InfoCompte.pays}" placeholder="Pays" required>
            <input type="hidden" name="email" value="${sessionScope.email}" />
            <button class="lien" type="submit" >Modifier</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/Modifinfo.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>
</body>
</html>
