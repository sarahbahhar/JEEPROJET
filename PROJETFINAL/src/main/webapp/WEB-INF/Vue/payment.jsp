<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 13/11/2023
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>payment</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<div style="width: 500px; margin: auto;">
    <h1>Paiement</h1>
    <form action="<%=request.getContextPath()%>/VerifyBankServlet" method="post">
        <fieldset>
            <legend>Vos données</legend>
            <table>
                <tr>
                    <td><label for="nom">Nom</label></td>
                    <td><input type="text" id="nom" name="nom" required></td>
                </tr>
                <tr>
                    <td><label for="numero">numéro de carte</label></td>
                    <td><input type="text" id="numero" name="numero" required></td>
                </tr>
                <tr>
                    <td><label for="date">date d'expiration</label></td>
                    <td><input type="text" id="date" name="date" required></td>
                    <td><label for="cvv">CVV</label></td>
                    <td><input type="text" id="cvv" name="cvv" required></td>


                </tr>
                <tr>
                    <td colspan="2"><input class="bouton-golden" type="submit" value="payer"></td>
                </tr>
            </table>
        </fieldset>
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
</body>
</html>
