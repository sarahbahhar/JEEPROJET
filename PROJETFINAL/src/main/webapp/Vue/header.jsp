<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport">
    <title>Accueil</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" href=<%=request.getContextPath()%>/"css/style.css">
    <script src="https://kit.fontawesome.com/33e6d2f05c.js" crossorigin="anonymous"></script>

</head>
<body>
<header>
    <nav class="header-nav">
        <ul>
            <li class="logo"><a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/img/logo_page.png" alt="logo_du_site" width="250px"></a></li>
            <li class="search">
                <div class="search-container">
                    <form class="search-form" method="get">
                        <input type="text" name="query" placeholder="Rechercher sur Milango.com">
                        <button type="submit"><i class="fa-sharp fa-solid fa-magnifying-glass"></i></button>
                    </form>
                </div>
            </li>
            <li class='style'></li>
            <li class='style'><a href='about.jsp' class='lien'>A propos</a></li>
            <c:choose>
                <c:when test="${!empty sessionScope.email}">
                    <li class='style'><a href='./Vue/signOut.jsp' class='lien'>Se Déconnecter</a></li>
                </c:when>
                <c:otherwise>
                    <li class='style'><a href='./Vue/signIn.jsp' class='lien'>Se Connecter</a></li>
                </c:otherwise>
            </c:choose>

            <div class="test">
                <li class="style"><a href="panier.jsp" class="lien">
                    <i class="fa-sharp fa-solid fa-cart-shopping"></i> Panier</a></li>
            </div>
        </ul>
    </nav>
</header>
</body>
</html>
