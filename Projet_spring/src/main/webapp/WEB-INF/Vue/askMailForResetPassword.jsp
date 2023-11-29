<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Réinitialisation du mot de passe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>

<% String error = (String) request.getAttribute("error");
    if (error != null && !error.isEmpty()) { %>
<p style="color: red;"><%= error %></p>
<% } %>

<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="${pageContext.request.contextPath}/PasswordForgetServlet" method="post">
            <h1>Mot de passe oublié</h1>
            <input type="email" id="email" name="email" placeholder="Email" required>
            <button class="lien" type="submit" >Envoyer la demande</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/mdp.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>

</body>
</html>
