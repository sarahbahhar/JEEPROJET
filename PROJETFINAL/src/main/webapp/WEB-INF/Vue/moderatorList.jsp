<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Moderateur" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moderator List</title>
    <link rel="stylesheet" href="./css/moderatorList.css">
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<h1>Moderator List</h1>
<table>
    <tr>
        <th>Email</th>
        <th>Note Moyenne</th>
        <th>Can Add Product</th>
        <th>Can delete Product</th>
        <th>Max Product</th>
        <th>Bannir</th>
    </tr>

    <c:forEach items="${moderators}" var="moderator">
        <tr>
            <td>${moderator.email}</td>
            <c:choose>
                <c:when test="${averageRatings[moderator.email] ne 0}">
                    <td>${averageRatings[moderator.email]}</td>
                </c:when>
                <c:otherwise>
                    <td>-</td>
                </c:otherwise>
            </c:choose>
            <td>${moderator.peutAjouterProduit}</td>
            <td>${moderator.peutSupprimerProduit}</td>
            <td>${moderator.maxProduitsLigne}</td>

            <td>
                <c:choose>
                    <c:when test="${moderator.dateBanni== null}">
                        <form action="${pageContext.request.contextPath}/page-bannir-servlet" method="post">
                            <input type="hidden" name="email" value="${moderator.email}" />
                            <button type="submit">Bannir</button>
                        </form>

                    </c:when>
                    <c:otherwise>
                        <p class="red-text" >Banni</p>
                    </c:otherwise>
                </c:choose>
            </td>

            <td>
                <form action="${pageContext.request.contextPath}/delete-moderator-servlet" method="post">
                    <input type="hidden" name="email" value="email" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<form class="style" action="${pageContext.request.contextPath}/redirect-servlet" method="post">
    <input type="hidden" name="path" value="editModerator.jsp" />
    <button class="lien" type="submit">Edit Moderators</button>
</form>
</body>
</html>
