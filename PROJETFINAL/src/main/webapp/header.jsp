<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport">
    <title>Accueil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">

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
                        <input type="text" id="query" name="query" placeholder="Rechercher sur Divan.com">
                        <button type="submit"><i class="fa-sharp fa-solid fa-magnifying-glass"></i></button>
                    </form>
                </div>
            </li>


            <c:choose>
                <c:when test="${empty sessionScope.email}">



                    <form >
                    </form>
                    <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                        <input type="hidden" name="path" value="signIn2.jsp" />
                        <button class='lien' type='submit'>A propos</button>
                    </form>
                        <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="signIn2.jsp" />
                            <button class='lien' type='submit'>Se Connecter</button>
                        </form>


                            <form  class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                                <input type="hidden" name="path" value="signIn2.jsp" />
                                <button class="lien" type="submit"><i class="fa-sharp fa-solid fa-cart-shopping"></i> Panier</button>
                            </form>

                </c:when>
                <c:when test="${sessionScope.role == 0}">


                        <form  class='style' action="${pageContext.request.contextPath}/mes-commandes-servlet" method="post">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Mes Commandes</button>
                        </form>



                            <form   class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                                <input type="hidden" name="path" value="myProfile.jsp" />
                                <button class="lien" type="submit">Profil</button>
                            </form>




                        <form class='style' action="${pageContext.request.contextPath}/sign-out-servlet" method="get">
                            <button class="lien" type="submit">Se Déconnecter</button>
                        </form>


                            <form class='style' action="${pageContext.request.contextPath}/panier-servlet" method="get">
                                <input type="hidden" name="email" value="${sessionScope.email}" />
                                <button class="lien" type="submit"><i class="fa-sharp fa-solid fa-cart-shopping"></i> Panier</button>
                            </form>

                </c:when>
                <c:when test="${sessionScope.role == 1}">

                    <li class="menu-deroulant">
                        <button class="lienG" type="submit">Gerer</button>

                        <div class="sous-menu">

                            <form class='style' action="${pageContext.request.contextPath}/mes-commandes-servlet" method="post">
                                <input type="hidden" name="email" value="${sessionScope.email}" />
                                <button class="lien" type="submit">Mes Commandes</button>

                            </form>
                            <form class='style' action="${pageContext.request.contextPath}/my-sales-servlet" method="post">
                                <input type="hidden" name="email" value="${sessionScope.email}" />
                                <button class="lien" type="submit">Mes Ventes</button>
                            </form>
                            <form class='style' action="${pageContext.request.contextPath}/my-product-list-servlet" method="post">
                                <input type="hidden" name="email" value="${sessionScope.email}" />
                                <button class="lien" type="submit">Gérer Produit</button>
                            </form>


                        </div>
                    </li>





                        <form   class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="myProfile.jsp" />
                            <button class="lien" type="submit">Profil</button>
                        </form>


                        <form  class='style' action="${pageContext.request.contextPath}/sign-out-servlet" method="get">
                            <button class="lien" type="submit">Se Déconnecter</button>
                        </form>


                        <form class='style' action="${pageContext.request.contextPath}/panier-servlet" method="get">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit"><i class="fa-sharp fa-solid fa-cart-shopping"></i> Panier</button>
                        </form>

                </c:when>
                <c:when test="${sessionScope.role == 2}">
                        <form class='style' action="${pageContext.request.contextPath}/in-waiting-mod-servlet" method="get">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Demande Moderateur</button>
                        </form>
                        <form class='style' action="${pageContext.request.contextPath}/moderator-servlet" method="get">
                            <input type="hidden" name="email" value="${sessionScope.email}" />
                            <button class="lien" type="submit">Liste Moderateur</button>
                        </form>
                        <form class='style' action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="myProfile.jsp" />
                            <button class="lien" type="submit">Profil</button>
                        </form>
                        <form class='style' action="${pageContext.request.contextPath}/sign-out-servlet" method="get">
                            <button class="lien" type="submit">Se Déconnecter</button>
                        </form>
                </c:when>
            </c:choose>
        </ul>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ">
                <li class="nav-item ">
                    <form class='style' action="${pageContext.request.contextPath}/velour-products" method="get">
                        <button type="submit"><a class="nav-link">Velour</a></button>
                    </form>
                </li>
                <li class="nav-item ">
                    <form class='style' action="${pageContext.request.contextPath}/cuir-products" method="get">
                        <button type="submit"><a class="nav-link">Cuir</a></button>
                    </form>
                </li>
                <li class="nav-item ">
                    <form class='style' action="${pageContext.request.contextPath}/tissu-products" method="get">
                        <button type="submit"><a class="nav-link">Tissu</a></button>
                    </form>
                </li>
                <li class="nav-item ">
                    <form class='style' action="${pageContext.request.contextPath}/lin-products" method="get">
                        <button type="submit"><a class="nav-link">Lin</a></button>
                    </form>
                </li>
                <li class="nav-item ">
                    <form class='style' action="${pageContext.request.contextPath}/banc-products" method="get">
                        <button type="submit"><a class="nav-link">Banc</a></button>
                    </form>
                </li>
                <li class="nav-item ">
                    <form class='style' action="${pageContext.request.contextPath}/exterieur-products" method="get">
                        <button type="submit"><a class="nav-link">Exterieur</a></button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</header>
</body>
</html>
