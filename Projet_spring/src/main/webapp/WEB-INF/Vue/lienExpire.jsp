<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>


    <meta charset="utf-8">
    <meta name="viewport">
    <title>Produits Lin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="./img/logo_onglet.ico" type="image/x-icon">
    <title>Lien Expiré</title>
    <style>
        .container {
            text-align: center;
            margin-top: 50px;
        }
        
    </style>
</head>
<body>
<div class="container">
    <h1>Lien de Réinitialisation Expiré</h1>
    <p>Le lien de réinitialisation de votre mot de passe est expiré ou invalide. Veuillez demander un nouveau lien.</p>
    <a href="/login">Retour à la page de connexion</a><br>
    <a href="/PasswordForgetServlet">Demander un nouveau lien de réinitialisation</a>
</div>
</body>
</html>
