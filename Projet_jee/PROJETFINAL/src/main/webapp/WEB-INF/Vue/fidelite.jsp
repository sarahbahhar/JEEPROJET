<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 22/11/2023
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <title>Fidélité</title>
</head>
    <body>
        <h2>Vos points de fidélité: ${pointFidelite} points </h2>
        <p>Vous pouvez utiliser 100 points pour obtenir une réduction de 10€. </p>
        <form action="${pageContext.request.contextPath}/FidelityServlet" method="post">

                <label>
                    <input type="checkbox" id="usePoints" name="usePoints" value="true" > Utilise 100 points de fidélité
                </label>
            <br>

            <h2>Total</h2>
            <c:if test="${usePoints}">
                <p id="total-amount">${totalReduit} €</p>
            </c:if>

            <c:if test="${!usePoints}">
                <p id="total-amount">${total} €</p>
            </c:if>
            <button class="bouton-golden" type="submit" value="Submit"> Suivant</button>
        </form>
    </body>
</html>
