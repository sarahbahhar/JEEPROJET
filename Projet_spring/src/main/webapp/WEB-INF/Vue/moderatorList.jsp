<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des modérateurs</title>
    <link rel="stylesheet" href="./css/style.css">
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table.css">
</head>
<body>
<div id="demo" style="border-radius: 20px;">
    <div style="border-radius: 20px;" class="table-responsive-vertical shadow-z-1">
        <!-- Table starts here -->
        <table style="border-radius: 20px;" id="table" class="table table-hover table-mc-light-blue">
            <thead>
            <tr>
                <th>Email</th>
                <th>Note Moyenne</th>
                <th>Droit ajout produit</th>
                <th>Droit supprimer produit</th>
                <th>Max Produit</th>
                <th>Bannir</th>
                <th>Supprimer</th>
                <th>Modifier</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${moderators}" var="moderator">
                <tr>
                   <td> <p style="font-size: 100%;">${moderator.email}</p></td>
                    <c:choose>
                        <c:when test="${averageRatings[moderator.email] ne 0}">
                            <td><p style="font-size: 100%;">${averageRatings[moderator.email]}</p></td>
                        </c:when>
                        <c:otherwise>
                            <td><p style="font-size: 100%;">-</p></td>
                        </c:otherwise>
                    </c:choose>
                    <td><p style="font-size: 100%;">
                        <c:choose>
                        <c:when test="${moderator.peutAjouterProduit==1}">
                            Oui
                        </c:when>
                        <c:otherwise>
                        Non</p>
                        </c:otherwise>
                            </c:choose></p></td>
                    <td><p style="font-size: 100%;"><c:choose>
                        <c:when test="${moderator.peutSupprimerProduit==1}">
                            Oui
                        </c:when>
                        <c:otherwise>
                        Non</p>
                        </c:otherwise>
                            </c:choose></p></td>
                    <td><p style="font-size: 100%;">${moderator.maxProduitsLigne}</p></td>

                    <td>
                        <c:choose>
                            <c:when test="${moderator.dateBanni== null}">
                                <form action="${pageContext.request.contextPath}/page-bannir-servlet" method="post">
                                    <input type="hidden" name="email" value="${moderator.email}" />
                                    <p style="font-size: 100%;"><button type="submit">Bannir</button></p>
                                </form>

                            </c:when>
                            <c:otherwise>
                                <p class="red-text" style="font-size: 100%;" >Banni</p>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <form action="${pageContext.request.contextPath}/delete-moderator-servlet" method="post">
                            <input type="hidden" name="email" value="${moderator.email}" />
                            <p style="font-size: 100%;"><button type="submit">Supprimer</button></p>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/redirection-form-edit-moderator-servlet" method="post">
                            <input type="hidden" name="email" value="${moderator.email}" />

                            <p style="font-size: 100%;"><button type="submit">Modifier</button></p>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
