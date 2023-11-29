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
    <title>Modifier Moderateur</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
</head>


<body>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="${pageContext.request.contextPath}/edit-moderator-servlet" method="post">
            <h1>Modifier les droits</h1>
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
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/modifDroits.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>
</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>
