<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 25/11/2023
  Time: 08:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>


<body>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="${pageContext.request.contextPath}/edit-moderator-servlet" method="post">
            <h1>Bannir un Modérateur</h1>
            <p>Email du modérateur : ${moderator.email}</p>
            <select id="addP" name="addP">
                <option value="1" ${moderator.peutAjouterProduit==1 ? 'selected' : ''}>Oui</option>
                <option value="0" ${moderator.peutAjouterProduit!=1 ? 'selected' : ''}>Non</option>
            </select>
            <select id="deleteP" name="deleteP">
                <option value="1" ${moderator.peutSupprimerProduit==1 ? 'selected' : ''}>Oui</option>
                <option value="0" ${moderator.peutSupprimerProduit!=1 ? 'selected' : ''}>Non</option>
            </select>
            <input type="number" id="maxProd" name="maxProd" value="${moderator.maxProduitsLigne}" />


            <input type="hidden" name="email" value="${moderator.email}" />
            <button class="lien" type="submit" >Modifier</button>
        </form>
        <form action="${pageContext.request.contextPath}/moderator-servlet" method="get">
            <input type="hidden" name="email" value="${sessionScope.email}" />
            <button class="lien" type="submit">Retour</button>
        </form>
    </div>

</div>
</body>
</html>
