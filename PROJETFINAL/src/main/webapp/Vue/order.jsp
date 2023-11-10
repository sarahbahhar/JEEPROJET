<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/9/2023
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/order-servlet" method="post">
    <label for="numeroCarte">Numéro de carte:</label>
    <input type="text" id="numeroCarte" name="numeroCarte" required><br>

    <label for="dateExpiration">Date d'expiration:</label>
    <input type="text" id="dateExpiration" name="dateExpiration" required><br>

    <label for="cvv">CCV:</label>
    <input type="text" id="cvv" name="cvv" required><br>

    <button type="submit">Passer la commande</button>
</form>
</body>
</html>
