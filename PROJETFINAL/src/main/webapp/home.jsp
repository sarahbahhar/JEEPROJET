
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/6/2023
  Time: 5:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport">
    <title>Accueil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<body>





<c:if test="${empty produits}">
    <script type="text/javascript">
        window.location.href = '<%= request.getContextPath() %>/product-servlet';
    </script>
</c:if>

<c:if test="${!empty sessionScope.InfoCompte.prenom && !empty sessionScope.InfoCompte.nom}">
    <div><h1>Bienvenue ${sessionScope.InfoCompte.prenom} ${sessionScope.InfoCompte.nom}</h1></div>
</c:if>







<p><%=request.getContextPath()%></p>








<div class="box">
    <c:forEach items="${produits}" var="produit">
        <form action="${pageContext.request.contextPath}/product-details" method="post">
            <div class="produit">
                <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;" >

                    <img src="./img/${produit.nomImage}" alt="${produit.titre}" style="width: 100px; height: 100px; margin-right: 10px;">
                    <div>
                        <h5>${produit.titre}</h5>
                        <p>${produit.miniDescription}</p>
                        <p>Prix : ${produit.prix} €</p>
                    </div>
                </button>
                <input type="hidden" name="produit_id" value="${produit.id}">
                <input type="hidden" name="type" value="pageProduit">
            </div>

        </form>
    </c:forEach>


</div>







</body>
</html>
