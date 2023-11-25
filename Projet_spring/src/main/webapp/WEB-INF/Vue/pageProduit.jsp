<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="./css/pageProduit.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="shortcut icon"  href="./img/logo_onglet.ico" type="image/x-icon">
</head>
<body>
<div>
    <hr> <!-- Repère visuel temporaire -->
    <div class="page">

        <c:choose>
            <c:when test="${empty produit}">
                <p>Aucun produit sélectionné.</p>
            </c:when>
            <c:otherwise>
                <div class="image">
                    <img src="./img/${produit.nomImage}" style="width: 300px; height: 300px;">
                </div>
                <div class="detail">
                    <h1>${produit.titre}</h1>
                    <hr>
                    <h2>${produit.description}</h2>
                    <hr>
                    <p>Prix : ${produit.prix} €</p>
                    <hr>
                </div>
                <div class="info">
                    <c:if test="${noteMoyenne ne 0}">
                        <div class="prix">
                            Note : ${noteMoyenne}
                        </div>
                    </c:if>
                    <div class="prix">${produit.prix} €</div>
                    <div class="stock">${produit.stock > 0 ? 'En stock' : 'Rupture de stock'}</div>



                    <c:choose>


                                <c:when test="${empty sessionScope.role}">
                                    <form action="${pageContext.request.contextPath}/redirect-servlet" method="post">

                                        <input type="hidden" name="path" value="signIn.jsp" />


                                        <c:if test="${not isModBanned}">
                                            <div class="quantite">
                                                <label >Quantité :</label>
                                                <input type="number" name="quantite"  min="1" max="${produit.stock}" value="1">
                                            </div>
                                            <input type="submit" name="ajouter_au_panier" value="Ajouter au panier">
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
                                            <div class="quantite">
                                                <label >Quantité :</label>
                                                <input type="number" name="quantite"  min="1" max="${produit.stock}" value="1">
                                            </div>
                                            <input type="submit" name="ajouter_au_panier" value="Ajouter au panier">
                                        </c:if>
                                    </form>
                                </c:otherwise>


                    </c:choose>




                </div>
            </c:otherwise>

        </c:choose>
    </div>
    <hr> <!-- Repère visuel temporaire -->

    <c:forEach var="commentaire" items="${commentaires}">
        <div class="commentaire">
            <p>Note : ${commentaire.note} / 5 </p>
            <p>Commentaire : ${commentaire.commentaire}</p>
        </div>
    </c:forEach>

    <c:if test="${not empty sessionScope.email}">
        <c:if test="${canComment==true}">
            <div class="comment-section">
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
                    <input type="submit" value="Soumettre">
                </form>
            </div>
        </c:if>
    </c:if>
</div>
</body>
</html>
