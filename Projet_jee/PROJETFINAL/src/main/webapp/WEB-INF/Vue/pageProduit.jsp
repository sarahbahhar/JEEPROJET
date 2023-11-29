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
                                        <div class="stock">
                                            <label >Quantité :</label>
                                            <input style="width: 30%" type="number" name="quantite"  min="1" max="${produit.stock}" value="1">
                                        </div>
                                        <button class="lien" type="submit" name="ajouter_au_panier">Ajouter au panier</button>
                                    </c:if>
                                    <c:if test="${isModBanned}">
                                        <p>Produit momentanément indisponible</p>
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




    <div class="bodyC">
        <div class="commentBox">
            <div class="post">
                <c:if test="${not empty sessionScope.email}">
                <c:if test="${canComment==true}">

                    <h3>Laissez un commentaire : </h3>
                    <form method="post" action="<%=request.getContextPath()%>/commentaire-servlet">
                        <label for="comment">Votre commentaire</label>
                        <textarea id="comment" type="text" placeholder="Commentaire" required name="commentaire"></textarea><br>
                        <label for="rating">Note :</label>
                        <div class="etoiles" id="userRatingStars" data-rating="0">
                            <c:forEach var="i" begin="1" end="5">
                                <span class="etoile" onclick="setUserRating(${i})">&#9733;</span>
                            </c:forEach>
                        </div>
                        <input type="hidden" id="hiddenUserRating" name="rating" value="0" />

                        <input type="hidden" name="produit_id" value="${produit.id}">
                        <button type="submit" style="width: 400px;">Soumettre</button>
                    </form>

                </c:if>
                </c:if><br><br>
                <h1>Commentaires</h1>
            </div>

            <c:forEach var="commentaire" items="${commentaires}">
                <div class="comment">
                    <div class="commentBody">
                        <div class="commentHeader">
                            <h3 class="commentAuthor">${commentaire.email}</h3>
                            <span class="publishDate">
                    <c:forEach var="i" begin="1" end="5">
                        <span class="etoile ${i <= commentaire.note ? 'active' : ''}">&#9733;</span>
                    </c:forEach>
                </span>
                        </div>
                        <span class="commentContent">${commentaire.commentaire}</span>
                    </div>
                </div>
            </c:forEach>

            <!-- Comment Components -->

            <!-- Add other Comment Components as needed -->
        </div>
    </div>
    <script>
        // Ajouter la fonction JavaScript pour la notation par étoiles ici
        function setRating(rating) {
            const starsContainer = document.getElementById('ratingStars');
            const stars = starsContainer.getElementsByClassName('etoile');

            for (let i = 0; i < stars.length; i++) {
                if (i < rating) {
                    stars[i].classList.add('active');
                } else {
                    stars[i].classList.remove('active');
                }
            }
        }

        function setUserRating(rating) {
            const starsContainer = document.getElementById('userRatingStars');
            const stars = starsContainer.getElementsByClassName('etoile');
            const hiddenRatingInput = document.getElementById('hiddenUserRating');

            for (let i = 0; i < stars.length; i++) {
                if (i < rating) {
                    stars[i].classList.add('active');
                } else {
                    stars[i].classList.remove('active');
                }
            }

            hiddenRatingInput.value = rating;
        }
    </script>
</body>
</html>
