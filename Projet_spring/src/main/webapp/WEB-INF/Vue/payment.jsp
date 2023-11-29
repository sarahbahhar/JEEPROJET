<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 13/11/2023
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Paiement</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/myCard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/creditcardlist.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <script src="<%=request.getContextPath()%>/js/myCard.js"></script>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
    <%@ include file="header.jsp" %>
</head>
<body>

<div class="carte">
    <c:if test="${empty cartesBancaires}">
        <h1>Vous n'avez pas de carte enregistrée sur votre compte. Merci d'en saisir une.</h1>
    </c:if>

    <c:if test="${not empty cartesBancaires}">
        <h1>Choisissez une carte bancaire :</h1>
        <form id="paymentForm" action="<%=request.getContextPath()%>/VerifyBankServlet" method="post" >
            <div id="error-message" style="display: none; color: red;"></div>
            <table>
                <c:forEach items="${cartesBancaires}" var="carte">
                    <tr>
                        <td>
                            <input type="radio" name="carteId" value="${carte.numero}">
                        </td>
                        <td>

                            <input type="hidden" name="numero" value="${carte.numero}" />
                            <input type="hidden" name="cvv" value="${carte.cvv}" />
                            <input type="hidden" name="date" value="${carte.date}" />
                            <input type="hidden" name="id" value="${carte.id}" />
                        </td>
                        <td>
                            <div class="credit-card visa selectable">
                                <div class="credit-card-last4">
                                        ${fn:substring(carte.numero, fn:length(carte.numero) - 4, fn:length(carte.numero))}
                                </div>

                                <div class="credit-card-expiry">
                                        ${carte.date}
                                </div>
                                <div class="credit-card-name"> <!-- Added div for cardholder's name -->
                                        ${carte.nom} <!-- Placing the cardholder's name here -->
                                </div></div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <button style="margin-left:35%" class="lien" type="submit">Valider</button>
        </form>
    </c:if>
</div>
<br>
<button style="width: 10%;margin-left:43%;" class="lien" onclick="toggleAddCardForm()">Ajouter Carte</button>

<div id="addCardForm">

        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <form action="${pageContext.request.contextPath}/add-card-during-servlet" method="post">
                    <h1>Ajouter un Mode de Paiement</h1>
                    <input type="text" id="nom" name="nom" placeholder="Nom sur la Carte" required>
                    <input type="text" id="numero" name="numero" pattern="[0-9]{16}" placeholder="Numéro de Carte" required>
                    <input type="text" id="expiration" name="expiration" pattern="(0[1-9]|1[0-2])\/[0-9]{2}" placeholder="Date d'Expiration (MM/AA)" required>
                    <input type="text" id="cvv" name="cvv" pattern="[0-9]{3}" placeholder="CVV" required>
                    <input type="hidden" id="email" name="email" value="${sessionScope.email}" />
                    <input type="hidden" id="redirectionPath" name="redirectionPath" value="selectCardServlet">
                    <button class="lien" onclick="validateAndToggle()" type="submit" >Payer</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <img src="${pageContext.request.contextPath}/img/nouvelleCarte.jpg" alt="canape">
                    </div>
                </div>
            </div>
        </div>
</div>


</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>



