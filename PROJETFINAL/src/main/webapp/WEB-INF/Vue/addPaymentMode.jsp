
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Payment Mode</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/new-bank-account-servlet" method="post">
    <br>
    <br>
    <label>Nom sur la Carte:</label>
    <input type="text" id ="nom" name="nom" required>
    <br>

    <label>Numéro de Carte:</label>
    <input type="text" id="numero" name="numero" pattern="[0-9]{16}" required>
    <br>

    <label >Date d'Expiration (MM/AAAA):</label>
    <input type="text" id="expiration" name="expiration" pattern="(0[1-9]|1[0-2])\/[0-9]{4}" required>
    <br>

    <label>CVV:</label>
    <input type="text" id="cvv" name="cvv" pattern="[0-9]{3}" required>
    <br>

    <input hidden type="text" id="email" name="email" value="${sessionScope.email}">
    <input type="submit" value="Payer">
</form>
</body>
</html>
