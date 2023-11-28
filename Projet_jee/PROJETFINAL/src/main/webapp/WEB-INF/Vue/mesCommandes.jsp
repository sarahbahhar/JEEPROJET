<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Commandes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/commande.css">
    <link rel="icon" type="image/png" href="./img/logo2.png">
</head>
<body>
<c:choose>
    <c:when test="${empty commandes}">
        <div class="no-commandes">
            <h1>Vous n'avez pas de commande en cours.</h1>
        </div>
    </c:when>
    <c:otherwise>
        <section>
            <h1>Commandes</h1>
            <c:forEach items="${commandes}" var="commande">
                <details>
                    <summary>
                        <div>
                                <span style="background-color: #f2dcbb;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="192" height="192" fill="currentColor" viewBox="0 0 256 256">
                                        <rect width="256" height="256" fill="none"></rect>
                                        <!-- ... Icône SVG ... -->
                                    </svg>
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
                            <span>-${commande.total} €</span>
                        </div>
                    </summary>

                    <c:forEach items="${produitcommandes}" var="produitCommande">
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
                                <dt>Detail</dt>
                                <dd>
                                    <form class="style" action="${pageContext.request.contextPath}/redirect-product-order-servlet" method="post">
                                        <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;"></button>
                                        <input type="hidden" name="produit_id" value="${produitCommande.idProduit}" />
                                        <input type="hidden" name="commande_id" value="${produitCommande.commandeNumero}" />
                                    </form>
                                </dd>
                            </div>
                        </dl></c:if>
                    </c:forEach>
                </details>
            </c:forEach>
        </section>
    </c:otherwise>
</c:choose>
</body>
</html>
