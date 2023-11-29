<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/singIn.css">
    <%@ include file="header.jsp" %>
</head>
<body>

</div>
<div class="container" id="container">
    <div class="form-container sign-in-container">
        <form action="${pageContext.request.contextPath}/contact-servlet" method="post">
            <h1>Contactez-nous</h1>
            <c:choose>
                <c:when test="${empty sessionScope.email}">
                    <input type="email" id="email" name="emailCompte" value="${sessionScope.email}" placeholder="Votre email" required>

                </c:when>
                <c:otherwise>
                    <input type="hidden" name="emailCompte" value="${sessionScope.email}" />
                </c:otherwise>

            </c:choose>
            <textarea class="custom-textarea-g" name="message" placeholder="Votre message" required></textarea>
            <button class="lien" type="submit">Envoyer</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-right">
                <img src="${pageContext.request.contextPath}/img/contact.jpg" alt="canape">
            </div>
        </div>
    </div>
</div>
</body>
<footer>
    <%@ include file="footer.jsp" %>
</footer>
</html>
