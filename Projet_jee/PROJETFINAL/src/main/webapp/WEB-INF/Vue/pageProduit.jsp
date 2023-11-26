<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produit</title>
    <link rel="shortcut icon"  href="./img/logo_onglet.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/prod.css">
</head>
<body>
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
                                            <input style="width: 20%" type="number" id="kactane" name="quantite"  min="1" max="${produit.stock}" value="1">
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
                                        <div class="stock">
                                            <label >Quantité :</label>
                                            <input style="width: 20%" type="number" name="quantite"  min="1" max="${produit.stock}" value="1">
                                        </div>
                                        <button class="lien" type="submit" name="ajouter_au_panier">Ajouter au panier</button>
                                    </c:if>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    </c:otherwise>
                    </c:choose>
                    <div class="sag">
                        <img src="./img/${produit.nomImage}"style="width: 745px; height: 465px; object-fit: cover;" >
                    </div>
                </div>
            </div>
</div>
    <c:forEach var="commentaire" items="${commentaires}">

        <p>Note : ${commentaire.note} / 5 </p>
        <p>Commentaire : ${commentaire.commentaire}</p>

    </c:forEach>

    <c:if test="${not empty sessionScope.email}">
        <c:if test="${canComment==true}">

            <h3>Laissez un commentaire :</h3>
            <form method="post" action="<%=request.getContextPath()%>/commentaire-servlet">
                <textarea name="commentaire" rows="4" cols="50"></textarea><br>
                <label for="rating">Note :</label>
                <select id="rating" name="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select><br>
                <input type="hidden" name="produit_id" value="${produit.id}">
                <button type="submit" class="lien">Soumettre</button>
            </form>

        </c:if>
    </c:if>
</body>
</html>
