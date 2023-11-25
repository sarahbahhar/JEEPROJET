<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/9/2023
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation de Commande</title>*
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<h1>Merci pour votre commande !</h1>
<p>Votre commande a été traitée avec succès.</p>

<%
    // Récupérer les informations de commande depuis la requête (à adapter en fonction de la logique de ton servlet)
    String numeroCommande = (String) request.getAttribute("numeroCommande");
    // Ajouter d'autres attributs si nécessaire

    // Afficher les informations de commande
    if (numeroCommande != null) {
%>
<p>Numéro de commande : <%= numeroCommande %></p>
<!-- Ajouter d'autres informations de commande ici -->
<%
    }
%>


</body>
</html>

