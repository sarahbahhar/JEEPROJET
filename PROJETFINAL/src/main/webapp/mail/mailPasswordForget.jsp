<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Réinitialisation du mot de passe</title>
</head>
<body>
<h1>Réinitialisation du mot de passe</h1>
<p>Vous avez demandé une réinitialisation de mot de passe pour votre compte. Cliquez sur le bouton ci-dessous pour réinitialiser votre mot de passe :</p>
<form action="${resetLink}" method="get">
    <input type="submit" value="Réinitialiser le mot de passe">
</form>

<p>Si vous n'avez pas demandé cette réinitialisation, veuillez ignorer cet e-mail.</p>
</body>
</html>


