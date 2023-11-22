<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier Mon Profil</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="shortcut icon" href="./img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<div style="width: 40%; margin: auto;">
    <h1>Modifier Mon Profil</h1>
    <fieldset>
        <legend>Informations à Modifier</legend>
        <form id="modifier-infoCompte-form" method="POST" action="${pageContext.request.contextPath}/ModifyInfoServlet">
            <table>
                <tr>
                    <td><label for="prenom">Prénom :</label></td>
                    <td><input type="text" id="prenom" name="prenom" value="${sessionScope.InfoCompte.prenom}" required></td>
                </tr>
                <tr>
                    <td><label for="nom">Nom :</label></td>
                    <td><input type="text" id="nom" name="nom" value="${sessionScope.InfoCompte.nom}" required></td>
                </tr>
                <tr>
                    <td><label for="date">Date de naissance :</label></td>
                    <td><input type="date" id="date" name="date" value="${sessionScope.InfoCompte.dateNaissance}" required></td>
                </tr>
                <tr>
                    <td><label for="telephone">N° téléphone :</label></td>
                    <td><input type="text" id="telephone" name="telephone" value="${sessionScope.InfoCompte.telephone}" required></td>
                </tr>
                <tr>
                    <td><label for="adresse">Adresse :</label></td>
                    <td><input type="text" id="adresse" name="adresse" value="${sessionScope.InfoCompte.adresse}" required></td>
                </tr>
                <tr>
                    <td><label for="ville">Ville :</label></td>
                    <td><input type="text" id="ville" name="ville" value="${sessionScope.InfoCompte.ville}" required></td>
                </tr>
                <tr>
                    <td><label for="codePostal">Code postal :</label></td>
                    <td><input type="number" id="codePostal" name="codePostal" value="${sessionScope.InfoCompte.codePostal}" required></td>
                </tr>
                <tr>
                    <td><label for="pays">Pays :</label></td>
                    <td><input type="text" id="pays" name="pays" value="${sessionScope.InfoCompte.pays}" required></td>
                </tr>
                <tr><input type="hidden" name="email" value="${sessionScope.email}" /><tr>

            </table>
        </form>
    </fieldset>
    <div style="display: flex; justify-content: space-between; margin-top: 20px;">
        <form action="${pageContext.request.contextPath}/redirect-servlet" method="post">
            <input type="hidden" name="path" value="myProfile.jsp" />
            <button class="bouton-golden" type="submit">Retour</button>
        </form>
        <button class="bouton-golden" type="submit" form="modifier-infoCompte-form">Modifier</button>
    </div>

</div>
</body>
</html>
