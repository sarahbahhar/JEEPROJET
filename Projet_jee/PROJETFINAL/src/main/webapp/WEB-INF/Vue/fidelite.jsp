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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/singIn.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <title>Fidélité</title>
</head>
    <body>
    <div class="container" id="container">
            <div class="form-container sign-in-container">
                < action="${pageContext.request.contextPath}/FidelityServlet" method="post">
                    <h2>Vos points de fidélité: ${pointFidelite} points </h2>
                    <p>Vous pouvez utiliser 100 points pour obtenir une réduction de 10€. </p>
                    <input type="checkbox" id="usePoints" name="usePoints" value="true" > Utiliser 100 points de fidélité
                    <h2>Total</h2>
                    <c:if test="${usePoints}">
                        <p id="total-amount">${totalReduit} €</p>
                    </c:if>

                    <c:if test="${!usePoints}">
                        <p id="total-amount">${total} €</p>
                    </c:if>
                    <button class="lien" type="submit" >Suivant</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <img src="${pageContext.request.contextPath}/img/fidelite.jpg" alt="canape">
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
