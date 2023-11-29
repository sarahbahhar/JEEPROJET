<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Profil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profil.css">
    <link rel="shortcut icon"  href="./img/logo_onglet.ico" type="image/x-icon">
    <%@ include file="header.jsp" %>
    <style>
        .container-profil {
            background: url("${pageContext.request.contextPath}/img/profil.jpg") center center no-repeat;
            background-size: cover;
        }
    </style>

</head><body>
<div class="container-profil">
    <div class="card">
        <h1>Mon Profil</h1><br>
        <p><strong>Email: </strong>${sessionScope.InfoCompte.email}</p>
        <p><strong>Prénom: </strong>${sessionScope.InfoCompte.prenom}</p>
        <p><strong>Nom: </strong>${sessionScope.InfoCompte.nom}</p>
        <p><strong>Date de naissance: </strong>${sessionScope.InfoCompte.dateNaissance}</p>
        <p><strong>Numéro de téléphone: </strong>${sessionScope.InfoCompte.telephone}</p>
        <p><strong>Adresse: </strong>${sessionScope.InfoCompte.adresse}</p>
        <p><strong>Ville: </strong>${sessionScope.InfoCompte.ville}</p>
        <p><strong>Code postal: </strong>${sessionScope.InfoCompte.codePostal}</p>
        <p><strong>Pays: </strong>${sessionScope.InfoCompte.pays}</p>
        <c:if test="${sessionScope.role == 0 || sessionScope.role == 1}">
            <p><strong>Points de fidélité: </strong>${sessionScope.pointFidelite}</p>

        </c:if>
        <c:if test="${sessionScope.role == 1}">
            <p><strong>Ma note: </strong>
            <c:choose>
                <c:when test="${averageRating==0}">
                    <em>Aucune moyenne disponible</em>
                </c:when>
                <c:otherwise>
                    <strong>Moyenne: </strong> ${averageRating}/5 </p>
                </c:otherwise>
            </c:choose>
        </c:if>


        <c:choose>
            <c:when test="${sessionScope.role == 1}">

                <p><strong>Droit d'ajouter un produit: </strong>
                <c:choose>
                    <c:when test="${sessionScope.canAddProduct}">
                        Oui
                    </c:when>
                    <c:otherwise>
                        Non</p>
                    </c:otherwise>
                </c:choose>
                <p><strong>Droit de supprimer un produit: </strong>
                <c:choose>
                    <c:when test="${sessionScope.canDeleteProduct}">
                        Oui
                    </c:when>
                    <c:otherwise>
                        Non</p>
                    </c:otherwise>
                </c:choose>
                <p><strong>Nombre Maximal de produits en ligne: </strong>${sessionScope.maxProductsPerLine}</p>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${sessionScope.role != 2}">
                <form  style="text-align:center" action="${pageContext.request.contextPath}/bank-account-servlet" method="get">
                    <input type="hidden" name="email" value="${sessionScope.email}" />
                    <button class="lien" type="submit">Gérer mes cartes</button>
                </form>
            </c:when>
        </c:choose>
        <c:if test="${sessionScope.role == 0 && sessionScope.demandeModerateur == false }">
            <form  style="text-align:center" action="${pageContext.request.contextPath}/redirect-servlet" method="get">
                <input type="hidden" name="path" value="formAddModerator.jsp" />
                <button class="lien" type="submit">Faire demande moderateur</button>
            </form>
        </c:if>
        <form  style="text-align:center" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
            <input type="hidden" name="path" value="changeMyInfo.jsp" />
            <button class="lien" type="submit">Modifier mes informations</button>
        </form>
    </div>
</div>
</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>
