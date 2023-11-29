<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Produitcommande" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Mes Commandes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/commande.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-KPx5sYzNlKDziuAx9nu7ZW9z0H73i1zYAM+QRVOGLsQVtThxdhZL0DO0Qe2UJbVsd0LooUrF9PsFyqU/Z2N5Ug==" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-KPx5sYzNlKDziuAx9nu7ZW9z0H73i1zYAM+QRVOGLsQVtThxdhZL0DO0Qe2UJbVsd0LooUrF9PsFyqU/Z2N5Ug==" crossorigin="anonymous" />
</head>
<body>
<c:choose>
    <c:when test="${empty commandes}">
        <div class="no-commandes">
            <h1>Vous n'avez pas encore effectuer de commande.</h1>
        </div>
    </c:when>
    <c:otherwise>
        <div class="command">
        <section>
            <c:choose>
                <c:when test="${type eq 'pageMySales'}">
                    <h1>Mes Ventes </h1>
                </c:when>
                <c:otherwise>
                    <h1>Mes Commandes</h1>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${commandes}" var="commande">
                <details>
                    <summary>
                        <div>
                            <span style="background-color: #f2dcbb;">
                                <i class="fas fa-couch" style="font-size: 2rem;"></i>
                            </span>
                            <h3>
                                <strong>
                                    <c:choose>
                                        <c:when test="${type eq 'pageMySales'}">
                                            Vente ${loop.index + 1}
                                        </c:when>
                                        <c:otherwise>
                                            Commande ${commande.numero}
                                        </c:otherwise>
                                    </c:choose>
                                </strong>
                                <small>${commande.dateDePaiement}</small>
                            </h3>
                            <span>
                                    <c:choose>
                                        <c:when test="${type eq 'pageMySales'}">
                                        </c:when>
                                        <c:otherwise>
                                            ${commande.total} €
                                        </c:otherwise>
                                    </c:choose></span>
                        </div>
                    </summary>
                    <% float total = 0; %>
                    <c:forEach items="${produitcommandes}" var="produitCommande">
                        <c:choose>
                            <c:when test="${type eq 'pageMySales'}">

                                    <c:if test="${produitCommande.emailVendeur eq sessionScope.email && produitCommande.commandeNumero eq commande.numero}">
                                        <dl>
                                            <div>
                                                <dt>Titre</dt>
                                                <dd>${produitCommande.titre}</dd>
                                            </div>
                                            <div>
                                                <dt>Prix</dt>
                                                <dd>${produitCommande.prix} €</dd>
                                            </div>
                                            <div>
                                                <dt>Quantité</dt>
                                                <dd>${produitCommande.quantite}</dd>
                                            </div>
                                            <div>
                                                <dt><form class="style" action="${pageContext.request.contextPath}/redirect-product-order-servlet" method="post">
                                                    <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;">
                                                        <i class="fas fa-search"></i> <!-- Utilisez l'icône de recherche de Font Awesome -->
                                                    </button><input type="hidden" name="produit_id" value="${produitCommande.idProduit}" />
                                                    <input type="hidden" name="commande_id" value="${produitCommande.commandeNumero}" />
                                                </form></dt>
                                                <dd>

                                                </dd>
                                            </div>
                                        </dl></c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${produitCommande.commandeNumero eq commande.numero}">
                                    <dl>
                                        <div>
                                            <dt>Titre</dt>
                                            <dd>${produitCommande.titre}</dd>
                                        </div>
                                        <div>
                                            <dt>Prix</dt>
                                            <dd>${produitCommande.prix} €</dd>
                                        </div>
                                        <div>
                                            <dt>Quantité</dt>
                                            <dd>${produitCommande.quantite}</dd>
                                        </div>
                                        <div>
                                            <dd><form class="style" style="width: 100%;height: 100%;" action="${pageContext.request.contextPath}/redirect-product-order-servlet" method="post">
                                                <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;">
                                                    <i class="fas fa-search"></i> <!-- Utilisez l'icône de recherche de Font Awesome -->
                                                </button><input type="hidden" name="produit_id" value="${produitCommande.idProduit}" />
                                                <input type="hidden" name="commande_id" value="${produitCommande.commandeNumero}" />
                                            </form></dd>

                                        </div>
                                    </dl></c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </details>
            </c:forEach>
        </section></div>
    </c:otherwise>
</c:choose>
</body>
</html>
