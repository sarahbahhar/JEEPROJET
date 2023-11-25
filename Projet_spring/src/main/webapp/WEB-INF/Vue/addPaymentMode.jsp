<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Payment Mode</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/myCard.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<div style="width : 40%; margin: auto;">
    <h1>Ajouter un Mode de Paiement</h1>
    <fieldset>
        <legend>Informations de la Carte</legend>
        <form id="new-account-form" action="${pageContext.request.contextPath}/new-bank-account-servlet" method="post">
            <table>
                <tr>
                    <td><label for="nom">Nom sur la Carte:</label></td>
                    <td><input type="text" id="nom" name="nom" required></td>
                </tr>
                <tr>
                    <td><label for="numero">Numéro de Carte:</label></td>
                    <td><input type="text" id="numero" name="numero" pattern="[0-9]{16}" required></td>
                </tr>
                <tr>
                    <td><label for="expiration">Date d'Expiration (MM/AA):</label></td>
                    <td><input type="text" id="expiration" name="expiration" pattern="(0[1-9]|1[0-2])\/[0-9]{2}" required></td>
                </tr>
                <tr>
                    <td><label for="cvv">CVV:</label></td>
                    <td><input type="text" id="cvv" name="cvv" pattern="[0-9]{3}" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="email" value="${sessionScope.email}" />
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
    <div style="display: flex; justify-content: space-between; margin-top: 20px;">
        <form action="${pageContext.request.contextPath}/bank-account-servlet" method="get">
            <input type="hidden" name="email" value="${sessionScope.email}">
            <button class="bouton-golden" type="submit">Retour</button>
        </form>
        <button class="bouton-golden" type="submit" form="new-account-form">Ajouter</button>
    </div>

</div>
</body>
</html>
