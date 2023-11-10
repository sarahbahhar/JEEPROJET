<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/10/2023
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moderator</title>
</head>
<body>
<form action="/add-moderator-servlet" method="get">
    <label for="motivation">Motivation :</label>
    <input type="text" id="motivation" name="motivation" required>
    <br>
    <input type="submit" value="Soumettre">
</form>
</body>
</html>
