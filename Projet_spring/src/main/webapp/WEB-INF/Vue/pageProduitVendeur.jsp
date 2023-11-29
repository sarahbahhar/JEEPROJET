<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/prod.css">
    <link rel="shortcut icon"  href="./img/logo_onglet.ico" type="image/x-icon">
    <%@ include file="header.jsp" %>
</head>
<body>


    <hr> <!-- Repère visuel temporaire -->
</div>
<div class="page">
    <c:choose>
    <c:when test="${empty produit}">
        <p>Aucun produit sélectionné.</p>
    </c:when>
    <c:otherwise>
    <div class="genel">
        <div class="icerik">
            <div class="sol">
                <br><br>
                <h1>${produit.titre}</h1>
                <c:if test="${noteMoyenne ne 0}">
                    <p class="note">Note : ${noteMoyenne}/5</p>
                    <div class="etoiles">
                        <c:forEach var="i" begin="1" end="5">
                            <span class="etoile ${i <= noteMoyenne ? 'active' : ''}">&#9733;</span>
                        </c:forEach>
                    </div>
                </c:if>
                <h5>${produit.description}</h5>
                <div class="para">${produit.prix} €

                    <div class="stock"><p>${produit.stock > 0 ? 'En stock' : 'Rupture de stock'}</p></div>
                </div>
                <c:choose>
                    <c:when test="${empty sessionScope.role}">
                        <form style="width:50%" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
                            <input type="hidden" name="path" value="signIn2.jsp" />
                            <c:if test="${not isModBanned}">
                                <div class="stock">
                                    <label >Quantité :</label>
                                    <input style="width: 30%" type="number" id="kactane" name="quantite"  min="1" max="${produit.stock}" value="1">
                                </div>
                                <button type="submit" class="lien" name="ajouter_au_panier" >Ajouter au panier</button>
                            </c:if>
                        </form>
                    </c:when>
                    <c:when test="${sessionScope.role == 2}">
                    </c:when>
                    <c:otherwise>
                        <form method="post" action="${pageContext.request.contextPath}/panier-servlet">
                            <input type="hidden" name="produit_id" value="${produit.id}">
                            <input type="hidden" name="produit_nom" value="${produit.titre}">
                            <input type="hidden" name="prix_produit" value="${produit.prix}">
                            <input type="hidden" name="email" value="${sessionScope.email}">
                            <c:if test="${not isModBanned}">
                            </c:if>
                        </form>
                    </c:otherwise>
                </c:choose>
            <c:if test="${sessionScope.canDeleteProduct}">
                <form action="${pageContext.request.contextPath}/modify-stock-servlet" method="post">
                    <input type="hidden" name="produit_id" value="${produit.id}">
                    <div class="stock"><label>Stock : </label><input type="number" style="width: 20%" name="stock" value="${produit.stock}" min="${produit.stock}"> <!-- Champ pour le nouveau stock -->
                    <button class="lien" type="submit" name="gerer_stock_produit">Modifier le stock</button></div>
                </form>
                <form  method="post" action="${pageContext.request.contextPath}/delete-product-servlet">
                    <input type="hidden" name="id" value="${produit.id}">
                    <input type="hidden" name="email" value="${sessionScope.email}" />

                    <button class="lien" type="submit" name="supprimer_un_produit">Supprimer ce produit</button>
                </form>
            </c:if>
            </div>
            </c:otherwise>
            </c:choose>
            <div class="sag">
                <img src="./img/${produit.nomImage}"style="width: 745px; height: 465px; object-fit: cover;" >
            </div>
        </div>

    </div>
</div>
</body>
</html>
