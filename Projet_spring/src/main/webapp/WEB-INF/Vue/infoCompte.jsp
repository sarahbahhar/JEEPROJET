<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mes infos</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>

<div style="width: 40%; margin: auto;">
    <h1>Mes Informations</h1>
    <p>${isUnique}</p>
    <form method="POST" action="<%=request.getContextPath()%>/infoCompteServlet">
        <fieldset>
            <legend>Modifier vos informations</legend>
            <table>
                <tr>
                    <td><label for="date">Date de naissance</label></td>
                    <td><input type="date" id="date" name="date" required></td>
                </tr>
                <tr>
                    <td><label for="telephone">N° téléphone</label></td>
                    <td><input type="text" id="telephone" name="telephone" required></td>
                </tr>
                <tr>
                    <td><label for="adresse">Adresse</label></td>
                    <td><input type="text" id="adresse" name="adresse" required></td>
                </tr>
                <tr>
                    <td><label for="ville">Ville</label></td>
                    <td><input type="text" id="ville" name="ville" required></td>
                </tr>
                <tr>
                    <td><label for="codePostal">Code Postal</label></td>
                    <td><input type="number" id="codePostal" name="codePostal" required></td>
                </tr>
                <tr>
                    <td><label for="pays">Pays</label></td>
                    <td><input type="text" id="pays" name="pays" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input class="bouton-golden" type="submit" value="Mettre à jour"></td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>

<hr>
</body>
</html>
