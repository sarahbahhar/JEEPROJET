<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>

<div style="width: 40%; margin: auto;">
    <h1>Inscription</h1>
    <form method="POST" action="<%=request.getContextPath()%>/inscriptionServlet">
        <fieldset>
            <legend>Informations Personnelles</legend>
            <table>
                <tr>
                    <td><label for="nom">Nom</label></td>
                    <td><input type="text" id="nom" name="nom" required></td>
                </tr>
                <tr>
                    <td><label for="prenom">Prénom</label></td>
                    <td><input type="text" id="prenom" name="prenom" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input type="email" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Mot de passe</label></td>
                    <td><input type="password" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input class="bouton-golden" type="submit" value="S'inscrire"></td>
                </tr>
            </table>
        </fieldset>
    </form>

    <div style="margin-top: 10px;">
        <a class="bouton-golden" href="${pageContext.request.contextPath}">Retour à l'accueil</a>
    </div>
</div>

<hr>
</body>
</html>
