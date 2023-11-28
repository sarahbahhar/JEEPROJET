
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport">
    <title>Accueil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/ProductSliderHome.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

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

<!--<c:if test="${empty produits}">
    <script type="text/javascript">
        window.location.href = '<%= request.getContextPath() %>/product-servlet';
    </script>
</c:if>-->

<div class="img-home">
    <form action="${pageContext.request.contextPath}/product-servlet" method="get">
        <button type="submit"><img src="img/home-img.png" alt="Nos Produits"></button>
    </form>
</div>

<!--<c:if test="${!empty sessionScope.InfoCompte.prenom && !empty sessionScope.InfoCompte.nom}">
    <div><h1>Bienvenue ${sessionScope.InfoCompte.prenom} ${sessionScope.InfoCompte.nom}</h1></div>
</c:if>-->


<div id="product-carousel-container">
    <h2>Selection de canapé confortable</h2>
    <ul id="product-carousel">
        <li class="item-1">
            <img src="../../img/canap1.png">
        </li>
        <li class="item-2">
            <img src="../../img/canap2.png">
        </li>
        <li class="item-3">
            <img src="../../img/canap3.png">
        </li>
        <li class="item-4">
            <img src="../../img/canap4.png">
        </li>
        <li class="item-5">
            <img src="../../img/canap5.png">
        </li>
    </ul>

</div>
<script src="../../js/ProductSliderHome.js"></script>

</body>
</html>
