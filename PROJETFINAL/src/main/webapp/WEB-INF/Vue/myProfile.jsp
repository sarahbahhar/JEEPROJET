<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 10/11/2023
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
</head>
<body>
    <h1> My Profile</h1>
    <c:out value="${sessionScope.InfoCompte.email}">non connecté</c:out>
    <form method="POST" action="<%=request.getContextPath()%>/ModifyInfoServlet">
        <label for="prenom" >prenom </label>
        <input type="text" id="prenom" name="prenom" value="${sessionScope.InfoCompte.prenom}" required><br>
        <label for="nom">nom</label>
        <input type="text" id="nom" name="nom" value="${sessionScope.InfoCompte.nom}" required><br>
        <label for="date">date</label>
        <input type="date" id="date" name="date" value="${sessionScope.InfoCompte.dateNaissance}" required><br>
        <label for="telephone">n° téléphone</label>
        <input type="text" id="telephone" name="telephone" value="${sessionScope.InfoCompte.telephone}" required><br>
        <label for="adresse">adresse</label>
        <input type="text" id="adresse" name="adresse" value="${sessionScope.InfoCompte.adresse}" required><br>
        <label for="ville">ville</label>
        <input type="text" id="ville" name="ville" value="${sessionScope.InfoCompte.ville}" required><br>
        <label for="codePostal">codePostal</label>
        <input type="number" id="codePostal" name="codePostal" value="${sessionScope.InfoCompte.codePostal}" required><br>
        <label for="pays">pays</label>
        <input type="text" id="pays" name="pays" value="${sessionScope.InfoCompte.pays}" required><br>




        <input type="submit" value="verifier">
        <a href="home.jsp">Annuler</a>

    </form>

</body>
</html>

