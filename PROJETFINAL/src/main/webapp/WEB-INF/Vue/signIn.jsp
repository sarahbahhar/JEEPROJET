<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connection</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>

<div style="width: 500px; margin: auto;">
    <h1>Se connecter</h1>
    <form action="<%=request.getContextPath()%>/login" method="post">
        <fieldset>
            <legend>Identifiants</legend>
            <table>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input type="text" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Mot de passe</label></td>
                    <td><input type="password" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input class="bouton-golden" type="submit" value="Se connecter"></td>
                </tr>
            </table>
        </fieldset>
    </form>


    <form action="${pageContext.request.contextPath}/redirect-servlet" method="post">
        <input type="hidden" name="path" value="askMailForResetPassword.jsp" />
        <button class="bouton-golden" type="submit">Mot de passe oublié ?</button>
    </form>

    <!-- Faire en sorte que les boutons soient sur la même ligne -->
    <div>

        <a class="bouton-golden" href="${pageContext.request.contextPath}">Retour à l'accueil</a>
        <span style="padding-left: 35px;">Pas de compte ?</span>

        <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
            <input type="hidden" name="path" value="signUp.jsp" />
            <button class="bouton-golden" type="submit">S'inscrire</button>
        </form>
    </div>
</div>

<hr>
</body>
</html>
