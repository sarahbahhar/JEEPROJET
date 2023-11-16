<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Demande de réinitialisation de mot de passe</title>
</head>
<body>
<h1>Demande de réinitialisation de mot de passe</h1>

<% String error = (String) request.getAttribute("error");
    if (error != null && !error.isEmpty()) { %>
<p style="color: red;"><%= error %></p>
<% } %>

<form class='style' action="${pageContext.request.contextPath}/PasswordForgetServlet" method="post">
    <label for="email">Adresse e-mail :</label>
    <input type="email" id="email" name="email" required>
    <br>
    <input type="submit" value="Envoyer la demande">
</form>



</body>
</html>
