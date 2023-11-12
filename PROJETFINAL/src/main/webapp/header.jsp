<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport">
    <title>Accueil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <script src="https://kit.fontawesome.com/33e6d2f05c.js" crossorigin="anonymous"></script>
</head>
<body>
<header>
    <nav class="header-nav">
        <ul class="ulMain">
            <li class="logo">
                <a href="<%=request.getContextPath()%>">
                    <img src="<%=request.getContextPath()%>/img/logo_page.png" alt="logo_du_site" width="250px">
                </a>
            </li>
            <li class="search">
                <div class="search-container">
                    <form class="search-form" method="get">
                        <input type="text" name="query" placeholder="Rechercher sur Milango.com">
                        <button type="submit"><i class="fa-sharp fa-solid fa-magnifying-glass"></i></button>
                    </form>
                </div>
            </li>


            <c:choose>
                <c:when test="${empty sessionScope.email}">
                    <li class="style">
                        <form class="style">
                            <button class="lien" type="submit"></button>
                        </form>
                    </li>
                </c:when>
                <c:when test="${sessionScope.role == 0}">
                    <li class='style'>
                        <form class="style">
                            <button class="lien" type="submit"></button>
                        </form>
                    </li>
                </c:when>
                <c:when test="${sessionScope.role == 1}">
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/product-servlet" method="get">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Gérer Produit</button>
                        </form>
                    </li>
                </c:when>
                <c:when test="${sessionScope.role == 2}">
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <select id="menu" onchange="window.location.href = this.value;">
                                <option>Gérer</option>
                                <option value="#">Liste de modérateur</option>
                                <option value="#">Liste de demande</option>
                            </select>
                            <button class="lien" type="submit"></button>
                        </form>
                    </li>
                </c:when>
            </c:choose>


            <c:choose>
                <c:when test="${empty sessionScope.email}">
                    <li class="style">
                        <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="about.jsp" />
                            <button class="lien" type="submit">A propos</button>
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="myProfile.jsp" />
                            <button class="lien" type="submit">Profil</button>
                        </form>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${!empty sessionScope.email}">
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/sign-out-servlet" method="get">
                            <button class="lien" type="submit">Se Déconnecter</button>
                        </form>
                    </li>
                </c:when>
                <c:otherwise>
                    <li >
                        <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="signIn.jsp" />
                            <button class='lien' type='submit'>Se Connecter</button>
                        </form>
                    </li>

                </c:otherwise>
            </c:choose>
            <div class="test">
                <li class='style'>
                    <form class="style" action="${pageContext.request.contextPath}/panier-servlet" method="get">
                        <input type="hidden" name="email" value="${sessionScope.email}" />
                        <button class="lien" type="submit"><i class="fa-sharp fa-solid fa-cart-shopping"></i> Panier</button>
                    </form>
                </li>
            </div>
        </ul>
    </nav>
</header>
</body>
</html>
