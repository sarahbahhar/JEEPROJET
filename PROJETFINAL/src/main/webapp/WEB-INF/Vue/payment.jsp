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
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/logo_onglet.ico" type="image/x-icon">
</head><body>
<h1>Choisissez une carte bancaire :</h1>

<form action="<%=request.getContextPath()%>/VerifyBankServlet" method="post">
    <c:forEach items="${cartesBancaires}" var="carte">
        <div>
            <label>
                <input type="radio" name="carteId" value="${carte.numero}">
                <input type="hidden" name="numero" value="${carte.numero}" />
                <input type="hidden" name="cvv" value="${carte.cvv}" />
                <input type="hidden" name="date" value="${carte.date}" />

                    ${carte.nom} : **** **** **** ${carte.numero} (${carte.date})
            </label>
        </div>
    </c:forEach>
    <input type="submit" value="Valider">
</form>


</body>
</html>
