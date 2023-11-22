
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport">
    <title>Accueil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/ProductSliderHome.css">
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
<br>
<br>
<div class="img-home"><img src="img/home-img.jpg" alt="Nos Produits"></div>

<!--<c:if test="${!empty sessionScope.InfoCompte.prenom && !empty sessionScope.InfoCompte.nom}">
    <div><h1>Bienvenue ${sessionScope.InfoCompte.prenom} ${sessionScope.InfoCompte.nom}</h1></div>
</c:if>-->


<div id="product-carousel-container">
    <h2>Selection de canapé confortables</h2>
    <ul id="product-carousel">
        <li class="item-1">
            <img src="img/canap1.png">
        </li>
        <li class="item-2">
            <img src="img/canap2.png">
        </li>
        <li class="item-3">
            <img src="img/canap3.png">
        </li>
        <li class="item-4">
            <img src="img/canap4.png">
        </li>
        <li class="item-5">
            <img src="img/canap5.png">
        </li>
    </ul>

    <div id="product-carousel-nav">
        <button class="carousel-nav prev"></button>
        <button class="carousel-nav next"></button>
    </div>
</div>
<script src="js/ProductSliderHome.js"></script>

<div class="container-left">
<div class="slide-left">
    <section>
        <h2>À Propos de Nous</h2>
        <br>
        <p><em>Bienvenue sur <strong>Divan.com</strong>, votre destination pour le canapé
            de vos rêves. Passionnés par le <strong>confort</strong> et le <strong>style</strong>, nous
            vous offrons une expérience unique. Comprendre l'importance du
            canapé dans votre vie quotidienne, c'est l'endroit où vous vous
            détendez après une longue journée, partagez des moments en famille
            et accueillez vos amis. Notre sélection exceptionnelle allie <strong>confort</strong>
            , <strong>durabilité</strong> et <strong>esthétique</strong>. Travaillant avec des designers talentueux
            et des fabricants de confiance, nous proposons des canapés de <strong>haute
                qualité</strong>. Explorez notre <a>catalogue</a> pour trouver le style, la taille et
            la couleur parfaits. Notre service client exceptionnel rend votre
            expérience d'achat agréable. <a>Contactez-nous</a> pour toute question ou
            aide dans le choix du canapé idéal. Faites confiance à <strong>Divan.com</strong>
            pour tous vos besoins en canapés, créant ainsi un espace de
            vie confortable et élégant.
        </em></p>
    </section>
</div>
</div>
</body>
</html>
