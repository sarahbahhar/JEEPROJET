<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/12/2023
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<div class="box">
    <c:forEach items="${produits}" var="produit">
    <form>
        <div class="produit">
            <button type="submit" style="background-color: transparent; border: none; padding: 0; margin: 0; cursor: pointer;" >

                <img src="./img/${produit.nomImage}" alt="${produit.titre}" style="width: 100px; height: 100px; margin-right: 10px;">
                <div>
                    <h5>${produit.titre}</h5>
                    <p>${produit.miniDescription}</p>
                    <p>Prix : ${produit.prix} €</p>
                </div>
            </button>
            <input type="hidden" name="produit_id" value="${produit.id}">
        </div>

    </form>
    </c:forEach>
</body>
</html>
