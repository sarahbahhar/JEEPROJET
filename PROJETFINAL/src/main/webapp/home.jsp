
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const dropdownBtn = document.querySelector(".dropdown-btn");
        const dropdownContent = document.querySelector(".dropdown-content");

        dropdownBtn.addEventListener("click", function() {
            dropdownContent.classList.toggle("show");
        });
    });</script>
<body>




<c:if test="${empty produits}">
    <script type="text/javascript">
        window.location.href = '<%= request.getContextPath() %>/product-servlet';
    </script>
</c:if>

<c:if test="${!empty sessionScope.InfoCompte.prenom && !empty sessionScope.InfoCompte.nom}">
    <div><h1>Bienvenue ${sessionScope.InfoCompte.prenom} ${sessionScope.InfoCompte.nom}</h1></div>
</c:if>

<div>



<jsp:include page="productList.jsp"  />



</div>







</body>
</html>
