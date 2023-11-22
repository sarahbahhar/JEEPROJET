<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Profil</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="shortcut icon"  href="./img/logo_onglet.ico" type="image/x-icon">

</head><body>
<div>


    <div style="width: 40%; margin: auto;">
        <h1>Mon Profil</h1>
        <fieldset>
            <legend>Informations</legend>
            <table>

                <tr>
                    <td>Email :</td>
                    <td class="black-text">${sessionScope.InfoCompte.email}</td>
                </tr>
                <tr>
                    <td>Prénom :</td>
                    <td class="black-text">${sessionScope.InfoCompte.prenom}</td>
                </tr>
                <tr>
                    <td>Nom :</td>
                    <td class="black-text">${sessionScope.InfoCompte.nom}</td>
                </tr>
                <tr>
                    <td>Date de naissance :</td>
                    <td class="black-text">${sessionScope.InfoCompte.dateNaissance}</td>
                </tr>
                <tr>
                    <td>Numéro de téléphone :</td>
                    <td class="black-text">${sessionScope.InfoCompte.telephone}</td>
                </tr>
                <tr>
                    <td>Adresse :</td>
                    <td class="black-text">${sessionScope.InfoCompte.adresse}</td>
                </tr>
                <tr>
                    <td>Ville :</td>
                    <td class="black-text">${sessionScope.InfoCompte.ville}</td>
                </tr>
                <tr>
                    <td>Code postal :</td>
                    <td class="black-text">${sessionScope.InfoCompte.codePostal}</td>
                </tr>
                <tr>
                    <td>Pays :</td>
                    <td class="black-text">${sessionScope.InfoCompte.pays}</td>
                </tr>
            </table>
        </fieldset>
        <c:choose>
            <c:when test="${sessionScope.role == 1}">
                <fieldset>
                    <legend>Droits d'Accès</legend>
                    <table>
                        <tr>
                            <td>Droit d'ajouter un produit :</td>
                            <td class="black-text">
                                <c:choose>
                                    <c:when test="${sessionScope.canAddProduct}">
                                        Oui
                                    </c:when>
                                    <c:otherwise>
                                        Non
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>Droit de supprimer un produit :</td>
                            <td class="black-text">
                                <c:choose>
                                    <c:when test="${sessionScope.canDeleteProduct}">
                                        Oui
                                    </c:when>
                                    <c:otherwise>
                                        Non
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>
                            <td>Nombre Maximal de produits en ligne :</td>
                            <td class="black-text">${sessionScope.maxProductsPerLine}</td>
                        </tr>
                    </table>
                </fieldset>
            </c:when>
        </c:choose>
        <div style="display: flex; justify-content: space-between; margin-top: 20px;">
            <c:choose>

                <c:when test="${sessionScope.role != 2}">

                    <form  style="text-align:left" action="${pageContext.request.contextPath}/bank-account-servlet" method="get">
                        <input type="hidden" name="email" value="${sessionScope.email}" />

                        <button class="bouton-golden" type="submit">Gérer mes cartes</button>
                    </form>

                </c:when>
            </c:choose>
            <form  style="text-align:right" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                <input type="hidden" name="path" value="changeMyInfo.jsp" />
                <button class="bouton-golden" type="submit">Modifier mes informations</button>
            </form>

        </div>
    </div>

    <hr>
</div>

</body>
</html>
