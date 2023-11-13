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
                    <form class="search-form" action="${pageContext.request.contextPath}/research-product-servlet" method="get">
                        <input type="text" id="query" name="query" placeholder="Rechercher sur Milango.com">
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
                    <li class="style">
                        <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="about.jsp" />
                            <button class="lien" type="submit">A propos</button>
                        </form>
                    </li>
                    <li >
                        <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="signIn.jsp" />
                            <button class='lien' type='submit'>Se Connecter</button>
                        </form>
                    </li>
                    <div class="test">
                        <li class='style'>
                            <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                                <input type="hidden" name="path" value="signIn.jsp" />
                                <button class="lien" type="submit"><i class="fa-sharp fa-solid fa-cart-shopping"></i> Panier</button>
                            </form>
                        </li>
                    </div>
                </c:when>
                <c:when test="${sessionScope.role == 0}">
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/mes-commandes-servlet" method="post">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Mes Commandes</button>
                        </form>
                    </li>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="myProfile.jsp" />
                            <button class="lien" type="submit">Profil</button>
                        </form>
                    </li>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/sign-out-servlet" method="get">
                            <button class="lien" type="submit">Se Déconnecter</button>
                        </form>
                    </li>
                    <div class="test">
                        <li class='style'>
                            <form class="style" action="${pageContext.request.contextPath}/panier-servlet" method="get">
                                <input type="hidden" name="email" value="${sessionScope.email}" />
                                <button class="lien" type="submit"><i class="fa-sharp fa-solid fa-cart-shopping"></i> Panier</button>
                            </form>
                        </li>
                    </div>
                </c:when>
                <c:when test="${sessionScope.role == 1}">
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/mes-commande-servlet" method="post">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Mes Commandes</button>
                        </form>
                    </li>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/product-servlet" method="post">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Gérer Produit</button>
                        </form>
                    </li>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="myProfile.jsp" />
                            <button class="lien" type="submit">Profil</button>
                        </form>
                    </li>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/sign-out-servlet" method="get">
                            <button class="lien" type="submit">Se Déconnecter</button>
                        </form>
                    </li>
                    <div class="test">
                        <li class='style'>
                            <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                                <input type="hidden" name="path" value="panier.jsp" />
                                <button class="lien" type="submit"><i class="fa-sharp fa-solid fa-cart-shopping"></i> Panier</button>
                            </form>
                        </li>
                    </div>
                </c:when>
                <c:when test="${sessionScope.role == 2}">
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/in-waiting-mod-servlet" method="get">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Demande Moderateur</button>
                        </form>
                    </li>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/moderator-servlet" method="get">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Liste Moderateur</button>
                        </form>
                    </li>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="myProfile.jsp" />
                            <button class="lien" type="submit">Profil</button>
                        </form>
                    </li>
                    <li class='style'>
                        <form class="style" action="${pageContext.request.contextPath}/sign-out-servlet" method="get">
                            <button class="lien" type="submit">Se Déconnecter</button>
                        </form>
                    </li>
                </c:when>
            </c:choose>





        </ul>
    </nav>
</header>
</body>
</html>
