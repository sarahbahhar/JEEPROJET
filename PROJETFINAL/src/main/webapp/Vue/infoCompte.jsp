<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 08/11/2023
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mes infos</title>
</head>
<body>
<form method="POST" action="<%=request.getContextPath()%>/infoCompteServlet">

    <label for="date">date</label>
    <input type="date" id="date" name="date" required><br>
    <label for="telephone">n° téléphone</label>
    <input type="text" id="telephone" name="telephone" required><br>
    <label for="adresse">adresse</label>
    <input type="text" id="adresse" name="adresse" required><br>
    <label for="ville">ville</label>
    <input type="text" id="ville" name="ville" required><br>
    <label for="codePostal">codePostal</label>
    <input type="number" id="codePostal" name="codePostal" required><br>
    <label for="pays">pays</label>
    <input type="text" id="pays" name="pays" required><br>




    <input type="submit" value="verifier">
</form>

</body>
</html>
