<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8">
        <meta name="viewport">
        <title>Accueil</title>
        <link rel="shortcut icon" href="../../img/logo_onglet.ico" type="image/x-icon">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productList.css">
        <%@ include file="header.jsp" %>
</head>
<body>
<div class="container">
    <table>
        <c:forEach items="${produits}" var="produit" varStatus="loop">
        <c:if test="${loop.index % 4 == 0}">
        </tr><tr>
        </c:if>
        <td>
            <form action="${pageContext.request.contextPath}/product-details" method="post">
                <button style="border:none; background-color:#f7f6f2;"type="submit"><div class="card">
                    <img src="./img/${produit.nomImage}" alt="${produit.titre}">
                    <div class="image-box">
                        <img src="./img/${produit.nomImage2}" alt="${produit.titre}">
                    </div>
                    <div class="content-box">
                        <h2>${produit.titre}</h2>
                        <div class="color">
                            <p>${produit.miniDescription}</p>
                        </div>
                        <div class="size">
                            <p>${produit.prix}$</p>
                        </div>
                    </div>
                </div></button>
                <input type="hidden" name="produit_id" value="${produit.id}">
                <input type="hidden" name="type" value="pageProduit">

            </form>
        </td>
        </c:forEach>
    </table>
</div>

</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>