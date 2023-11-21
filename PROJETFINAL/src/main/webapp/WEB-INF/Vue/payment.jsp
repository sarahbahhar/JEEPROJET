<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 13/11/2023
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>payment</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/myCard.css">
    <script src="<%=request.getContextPath()%>/js/myCard.js"></script>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head><body>
<h1>Choisissez une carte bancaire :</h1>
<form action="<%=request.getContextPath()%>/VerifyBankServlet" method="post">
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
                        ${carte.nom}
                </td>
                <td>
                    **** **** **** ${carte.numero} (${carte.date})
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Valider">
</form>

<button onclick="toggleAddCardForm()">Ajouter Carte</button>

<div id="addCardForm">

    <form action="${pageContext.request.contextPath}/add-card-during-servlet" method="post">
        <br>
        <br>
        <label>Nom sur la Carte:</label>
        <input type="text" id ="nom" name="nom" required>
        <br>

        <label>Numéro de Carte:</label>
        <input type="text" id="numero" name="numero" pattern="[0-9]{1}" required>
        <br>

        <label >Date d'Expiration (MM/AA):</label>
        <input type="text" id="expiration" name="expiration" pattern="(0[1-9]|1[0-2])\/[0-9]{2}" required>
        <br>

        <label>CVV:</label>
        <input type="text" id="cvv" name="cvv" pattern="[0-9]{3}" required>
        <br>

        <input hidden type="text" id="email" name="email" value="${sessionScope.email}">
        <input type="hidden" id="redirectionPath" name="redirectionPath" value="selectCardServlet">

        <input onclick="toggleAddCardForm()" type="submit" value="Payer">
    </form>






</div>


</body>
</html>
